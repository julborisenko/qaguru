package guru.qa;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JunitCore {


    public static void main(String[] args) throws Exception {

        ArrayList<Method> beforeAllList = new ArrayList<>();
        ArrayList<Method> beforeEachList = new ArrayList<>();
        ArrayList<Method> testList = new ArrayList<>();
        ArrayList<Method> afterEachList = new ArrayList<>();
        ArrayList<Method> afterAllList = new ArrayList<>();

        Class clazz = SimpleTest.class;

        for (Method method : clazz.getDeclaredMethods()) {
            BeforeAll methodAnnotationBeforeAll = method.getAnnotation(BeforeAll.class);
            if (methodAnnotationBeforeAll != null) {
                beforeAllList.add(method);
            }
            BeforeEach methodAnnotationBeforeEach = method.getAnnotation(BeforeEach.class);
            if (methodAnnotationBeforeEach != null) {
                beforeEachList.add(method);
            }
            Test methodAnnotationTest = method.getAnnotation(Test.class);
            if (methodAnnotationTest != null) {
                testList.add(method);
            }
            AfterEach methodAnnotationAfterEach = method.getAnnotation(AfterEach.class);
            if (methodAnnotationAfterEach != null) {
                afterEachList.add(method);
            }
            AfterAll methodAnnotationAfterAll = method.getAnnotation(AfterAll.class);
            if (methodAnnotationAfterAll != null) {
                afterAllList.add(method);
            }
        }
        for (Method beforeAllmethod : beforeAllList) {
            executeMethod(beforeAllmethod);
        }

        for (Method testMethod : testList) {
            for (Method beforeEachmethod : beforeEachList) {
                executeMethod(beforeEachmethod);
            }
            executeMethod(testMethod);
            for (Method afterEachmethod : afterEachList) {
                executeMethod(afterEachmethod);
            }
        }

        for (Method afterAllmethod : afterAllList) {
            executeMethod(afterAllmethod);
        }

    }

    public static void executeMethod(Method inputMethod) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        try {
            inputMethod.invoke(inputMethod.getClass().getConstructor().newInstance());
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertionError) {
                System.out.println("Method failed: " + inputMethod.getName());
            } else {
                System.out.println("Method broken: " + inputMethod.getName());
            }
        }
        System.out.println("Method passed: " + inputMethod.getName());
    }
}
