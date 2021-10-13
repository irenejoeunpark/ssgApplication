package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        //reads the argument option
        if(args[0].equals("--version")|| args[0].equals("-v")){
            System.out.println("ssgApplication ver 1.0.0, Sept 2021");
        } else if(args[0].equals("--help") || args[0].equals("-h")){
            System.out.println("---------------------------------------");
            System.out.println("* -v OR --version - current version   *");
            System.out.println("* -i OR --input - input file or files *");
            System.out.println("---------------------------------------");

        } else if(args[0].equals("--input") || args[0].equals("-i")){

            //remove the option and store as file name by removing the first element of the argument array
            String[] fileNameFull = new String[args.length-1];
            for(int i=1;i<args.length;i++){
                fileNameFull[i-1] = args[i];
            }

            String inputFile = fileNameReader(fileNameFull);
            if(inputFile.endsWith(".txt")){
                //if input file is only one file
                File file = new File(inputFile);
                String fileName = file.getName();
                //create html
                createHtml(file);

            }
            else if(inputFile.endsWith(".md")){
                try{
                    //if input file is only one file
                    File file = new File(inputFile);
                    //create html
                    MDUtils.createHTMLFromMd(file);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            else {
                //if user added multiple files(directory)
                try {
                    List<File> allFiles = Files.walk(Paths.get(inputFile))
                            .filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .collect(Collectors.toList());
                    List<String> fileNames = new ArrayList<>() ;
                    for(File file :allFiles){
                        createHtml(file);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }else {
            System.out.println("Invalid argument passed. Please pass a valid argument");
        }


    }


    private static String fileNameReader(String[] str) {
        //convert string array to string with the space
        StringBuilder s = new StringBuilder();
        for(String arg: str) {
            s.append(arg).append(" ");
        }
        return s.substring(0,s.length() -1);
    }

    private static void createHtml(File fileName){
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

            String htmlFileName = "dist/" + name + ".html";

            //pre-formed html forms
            String beginParagraph = "<p>";
            String endPragraph = "</p><br/>";

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

            String htmlFormE = "</body>\r\n"
                    + "</html>\r\n"
                    + "";

            //create directory if not exist
            new File("dist").mkdir();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8"));
            writer.write(htmlFormB);
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
            writer.write(htmlFormE);
            writer.close();

            //for logging
            System.out.println(name + ".html has been created in dist!");

        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
