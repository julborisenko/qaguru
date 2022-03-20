import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipParsingTest {

    File file = new File("src/test/resources/files/zipfile.zip");

    @Test
    void parseXlsxInZipTest() throws Exception {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry zipEntry = zipFile.getEntry("sample3.xlsx");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        XLS xls = new XLS(inputStream);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(7)
                .getCell(1)
                .getStringCellValue()).contains("July");
    }

    @Test
    void parsePdfInZipTest() throws Exception {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry zipEntry = zipFile.getEntry("pdf-sample.pdf");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        PDF pdf = new PDF(inputStream);
        assertThat(pdf.title).isEqualTo("This is a test PDF file");
        assertThat(pdf.numberOfPages).isEqualTo(1);
    }

    @Test
    void parseCsvInZipTest() throws Exception {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry zipEntry = zipFile.getEntry("Sample-Spreadsheet-10-rows.csv");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
        List<String[]> content = reader.readAll();
        assertThat(content.get(2)).contains("Cardinal Slant-D Ring Binder, Heavy Gauge Vinyl");
    }
}