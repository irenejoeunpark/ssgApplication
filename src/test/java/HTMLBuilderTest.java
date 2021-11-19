import com.company.HTMLBuilder;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HTMLBuilderTest {

    @Test
    void testProcessInput() throws IOException {
        HTMLBuilder.processInput("sources/Sample.txt","dist");
        File target = new File("dist/Sample.html");
        assertTrue(FileUtils.contentEquals(target,new File("dist/Sample.html")));
    }

    @Test
    void testFileNameReader() {
        String[] fileName1 = {"example", "file", "name.txt"};
        assertEquals("example file name.txt",HTMLBuilder.fileNameReader(fileName1));

        String[] fileName2 = {"sample.md"};
        assertEquals("sample.md",HTMLBuilder.fileNameReader(fileName2));
    }

    @Test
    void testWriteHtmlHeader() {
    }

    @Test
    void testWriteHtmlFoot() {
    }
}