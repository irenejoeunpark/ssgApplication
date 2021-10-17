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
}