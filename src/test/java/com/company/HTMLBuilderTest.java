package com.company;

import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

import java.io.*;

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
    void testWriteHtmlHeaderAndFooter() {
        String actualFileName = "testDir/headerTestActual.html";
        String expectedFileName = "testDir/headerTestExpected.html";
        String expected = "<!doctype html>\r\n"
                + "<html lang=\"en\">\r\n"
                + "<head>\r\n"
                + "  <meta charset=\"utf-8\">\r\n"
                + "<STYLE type=\"text/css\">\n"
                + "   H1 {border-width: 1; border: solid; text-align: center; font-family: Arial, Helvetica, sans-serif}\n"
                + "   p{font-family: Arial, Helvetica, sans-serif;}\n"
                + "    body {background-color: #d6ecf3;padding-left: 10%;padding-right:10%; padding-top: 0.5%;line-height: 1.5;text-align: center;}\n"
                + " </STYLE>"
                + "  <title>"
                + actualFileName
                + "</title>\r\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "</body>\r\n"
                + "</html>\r\n"
                + "";
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(actualFileName), "UTF-8"));
            HTMLBuilder.writeHtmlHeader(writer, actualFileName);
            writer.close();
            BufferedWriter writeFooter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(actualFileName,true), "UTF-8"));
            HTMLBuilder.writeHtmlFoot(writeFooter);

            BufferedWriter writerExpected = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(expectedFileName), "UTF-8"));
            writerExpected.write(expected);
            writerExpected.close();

            assertTrue(FileUtils.contentEquals(new File(expectedFileName),new File(actualFileName)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
