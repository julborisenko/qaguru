import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParsingTest {

    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("src/test/resources/files/sample1.json");

    @Test
    void parseJsonTest() throws Exception{
        JsonNode jsonNode = objectMapper.readTree(file);
        assertThat(jsonNode.get("color").asText()).isEqualTo("Red");
    }

}
