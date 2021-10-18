package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLBuilder {

    static void processInput(String inputFile, String output) {
        if(inputFile.endsWith(".txt")){
            //Single file
            File file = new File(inputFile);
            TextUtils.createHtmlFromTxt(file, output);
        }
        else if(inputFile.endsWith(".md")){
            try{
                File file = new File(inputFile);
                MDUtils.createHTMLFromMd(file);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else {
            //If user passes input directory (multiple text files)
            try {
                List<File> allFiles = Files.walk(Paths.get(inputFile))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
                for(File file :allFiles){
                    TextUtils.createHtmlFromTxt(file, output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String fileNameReader(String[] str) {
        //convert string array to string with the space
        StringBuilder s = new StringBuilder();
        for(String arg: str) {
            s.append(arg).append(" ");
        }
        return s.substring(0,s.length() -1);
    }

    public static void writeHtmlHeader(BufferedWriter writer, String fileName){
        //Writes html header before body tag
        try {
            //Predefined html tags
            String htmlHeader = "<!doctype html>\r\n"
                    + "<html lang=\"en\">\r\n"
                    + "<head>\r\n"
                    + "  <meta charset=\"utf-8\">\r\n"
                    + "<STYLE type=\"text/css\">\n"
                    + "   H1 {border-width: 1; border: solid; text-align: center; font-family: Arial, Helvetica, sans-serif}\n"
                    + "   p{font-family: Arial, Helvetica, sans-serif;}\n"
                    + "    body {background-color: #d6ecf3;padding-left: 10%;padding-right:10%; padding-top: 0.5%;line-height: 1.5;text-align: center;}\n"
                    + " </STYLE>"
                    + "  <title>"
                    + fileName
                    + "</title>\r\n"
                    + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
                    + "</head>\r\n"
                    + "<body>\r\n"
                    + "";

            writer.write(htmlHeader);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHtmlFoot(BufferedWriter writer) {
        //Writes html footers after body
        try {
            String htmlFoot = "</body>\r\n"
                    + "</html>\r\n"
                    + "";
            writer.write(htmlFoot);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}