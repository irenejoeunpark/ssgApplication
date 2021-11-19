package com.company;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {

    @Test
    void createHtmlFromTxt() throws IOException {
        File txtFile = new File("testDir/sample.txt");
        BufferedWriter writeSample = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "UTF-8"));
        writeSample.write("This is a Title of the Sample Text");
        writeSample.newLine();
        writeSample.newLine();
        writeSample.write("First Paragraph.");
        writeSample.write("This is a sample test");
        writeSample.newLine();
        writeSample.write("2. Sample text");
        writeSample.newLine();
        writeSample.close();

        TextUtils.createHtmlFromTxt(txtFile,"");


        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("testDir/sample.html"), "UTF-8"));
        System.out.println(reader.readLine());
    }
}