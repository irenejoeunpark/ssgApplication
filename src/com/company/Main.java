package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
//
//        try{
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file.html"),"UTF-8"));
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        String txtFileName = fileNameReader(args);

        String htmlFormB = "<!doctype html>\r\n"
                + "<html lang=\"en\">\r\n"
                + "<head>\r\n"
                + "  <meta charset=\"utf-8\">\r\n"
                + "  <title>Filename</title>\r\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "";

        String htmlFormE = "</body>\r\n"
                + "</html>\r\n"
                + "";


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFileName),"UTF-8"));
            String aLine;

            String fileName = txtFileName.split("\\.")[0];
            String htmlFileName = "htmls/" + fileName + ".html";

            String beginParagraph = "<p>";
            String endPragraph = "</p>";

            new File("dist").mkdir();

            //TODO :: place files in ./dist
            //./dist/filName
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8"));

            writer.write(htmlFormB);
            writer.write(beginParagraph);
            while((aLine = reader.readLine()) != null) {
                aLine = aLine.trim();
                if(aLine.length() == 0) {
                    writer.write(endPragraph);
                    writer.newLine();
                    writer.write(beginParagraph);
                }
                writer.write(aLine);
            }
            writer.write(endPragraph);
            writer.write(htmlFormE);
            writer.close();


        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    private static String fileNameReader(String[] str) {
        StringBuilder s = new StringBuilder();
        for(String arg: str) {
            s.append(arg).append(" ");
        }
        return s.substring(0,s.length() -1);
    }
}
