package guru.qa;

public class CodeExercise {

    public static void main(String[] args){
        byteOverflowExercise();
        shortOverflowExercise();
        intOverflowExercise();
        longOverflowExercise();
        floatOverflowExercise();
        doubleOverflowExercise();
        operationTypeExercise();
        printTrueStatementsExercise();
    }

    public static void byteOverflowExercise() {
        byte a = Byte.MAX_VALUE;
        byte b = Byte.MIN_VALUE;
        byte c = 1;
        System.out.println("Max byte value = " + a + ", max byte value+1 = " + (byte) (a + c));
        System.out.println("Min byte value = " + b + ", min byte value-1 = " + (byte) (b - c));
    }

    public static void shortOverflowExercise() {
        short a = Short.MAX_VALUE;
        short b = Short.MIN_VALUE;
        short c = 1;
        System.out.println("Max short value = " + a + ", max short value+1 = " + (short) (a + c));
        System.out.println("Min short value = " + b + ", min short value-1 = " + (short) (b - c));
    }

    public static void intOverflowExercise() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MIN_VALUE;
        int c = 1;
        System.out.println("Max int value = " + a + ", max int value+1 = " + (a + c));
        System.out.println("Min int value = " + b + ", min int value-1 = " + (b - c));
    }

    public static void longOverflowExercise() {
        long a = Long.MAX_VALUE;
        long b = Long.MIN_VALUE;
        long c = 1;
        System.out.println("Max long value = " + a + ", max long value+1 = " + (a + c));
        System.out.println("Min long value = " + b + ", min long value-1 = " + (b - c));
    }

    public static void floatOverflowExercise() {
        float a = Float.MAX_VALUE;
        float b = Float.MIN_VALUE;
        float c = 1F;
        System.out.println("Max float value = " + a + ", max float value+1 = " + (a + c));
        System.out.println("Min float value = " + b + ", min float value-1 = " + (b - c));
    }

    public static void doubleOverflowExercise() {
        double a = Double.MAX_VALUE;
        double b = Double.MIN_VALUE;
        double c = 1D;
        System.out.println("Max double value = " + a + ", max double value+1 = " + (a + c));
        System.out.println("Min double value = " + b + ", min double value-1 = " + (b - c));
    }

    public static void operationTypeExercise() {
        byte abyte = 8;
        short ashort = 12;
        int anint = 3;
        long along = 21;
        float afloat = 2;
        double adouble = 4;
        System.out.println("Byte and short result is " + ((Object) (abyte + ashort)).getClass().getSimpleName());
        System.out.println("Long and int result is " + ((Object) (along - anint)).getClass().getSimpleName());
        System.out.println("Float and double result is " + ((Object) (afloat * adouble)).getClass().getSimpleName());
        System.out.println("Byte and double result is " + ((Object) (abyte / adouble)).getClass().getSimpleName());
        System.out.println("Long and float result is " + ((Object) (along % afloat)).getClass().getSimpleName());
    }


    public static void printTrueStatementsExercise() {
        int x = 9;
        int y = 5;

        System.out.println(x++ < ++x);
        x = 9;
        System.out.println((x += y) >= x);
        x = 9;
        System.out.println((x--) != x);
        x = 9;
        System.out.println((x - y) == --y);
        x = 9;
        System.out.println((x > y) & ((x % y) <= 2));
        x = 9;
        System.out.println((x -= y) > 0 || x / 0 > 2); //вторая часть никогда не выполнится
    }
}
