package com.company;

import java.io.*;

import static com.company.HTMLBuilder.writeHtmlHeader;
import static com.company.HTMLBuilder.writeHtmlFoot;

public class TextUtils {
    public static void createHtmlFromTxt(File fileName, String output) {
        try {
            //read file
            //used InputStreamReader because of an encoding issue for the closing double quote
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

            //remove .txt and  get the file name from the entire file address
            String name = fileName.toString().split("\\.")[0].split("\\\\")[fileName.toString().split("\\.")[0].split("\\\\").length - 1];
            String htmlFileName = output + "/" + name + ".html";

            String pOpen = "<p>";
            String pClose = "</p><br/>";

            //create directory if not exist
            new File(output).mkdir();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName), "UTF-8"));

            //Borrowed
            writeHtmlHeader(writer, name);

            String aLine = reader.readLine();
            writer.write(new StringBuilder().append("<h1>").append(aLine).append("</h1><br/><br/>").toString());

            writer.write(pOpen);
            while ((aLine = reader.readLine()) != null) {
                aLine = aLine.trim();
                //find the paragraph end
                if (aLine.length() == 0) {
                    writer.write(pClose);
                    writer.newLine();
                    writer.write(pOpen);
                }
                writer.write(aLine);
            }
            writer.write(pClose);
            writeHtmlFoot(writer);

            //for logging
            System.out.println(name + ".html has been created in " + output + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}