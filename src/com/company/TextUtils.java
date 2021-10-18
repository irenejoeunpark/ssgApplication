package com.company;

import java.io.*;

public class TextUtils {
    public static void createHtmlFromTxt(File fileName, String output) {
        try{
            //read file
            //used inputstremreader because of an encoding issue for the closing double quote
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
            String aLine;

            //remove .txt
            String fName = fileName.toString().split("\\.")[0];

            //only getting the file name from the entire file address
            String[] getOnlyName = fName.split("\\\\");
            String name = getOnlyName[getOnlyName.length-1];

            String htmlFileName = output + "/" + name + ".html";

            String beginParagraph = "<p>";
            String endPragraph = "</p><br/>";

            //create directory if not exist
            new File(output).mkdir();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8"));

            writeHtmlB(writer,name);

            aLine = reader.readLine();
            writer.write("<h1>"+aLine + "</h1><br/><br/>");

            writer.write(beginParagraph);
            while((aLine = reader.readLine()) != null) {
                aLine = aLine.trim();
                //find the paragraph end
                if(aLine.length() == 0) {
                    writer.write(endPragraph);
                    writer.newLine();
                    writer.write(beginParagraph);
                }
                writer.write(aLine);
            }
            writer.write(endPragraph);
            writeHtmlE(writer);

            //for logging
            System.out.println(name + ".html has been created in " + output + "!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void writeHtmlB(BufferedWriter writer, String name){
        try {
            String htmlFormB = "<!doctype html>\r\n"
                    + "<html lang=\"en\">\r\n"
                    + "<head>\r\n"
                    + "  <meta charset=\"utf-8\">\r\n"
                    + "<STYLE type=\"text/css\">\n"
                    + "   H1 {border-width: 1; border: solid; text-align: center; font-family: Arial, Helvetica, sans-serif}\n"
                    + "   p{font-family: Arial, Helvetica, sans-serif;}\n"
                    + "    body {background-color: #d6ecf3;padding-left: 10%;padding-right:10%; padding-top: 0.5%;line-height: 1.5;text-align: center;}\n"
                    + " </STYLE>"
                    + "  <title>"
                    + name
                    + "</title>\r\n"
                    + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
                    + "</head>\r\n"
                    + "<body>\r\n"
                    + "";

            writer.write(htmlFormB);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeHtmlE(BufferedWriter writer){
        try {
            String htmlFormE = "</body>\r\n"
                    + "</html>\r\n"
                    + "";
            writer.write(htmlFormE);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}