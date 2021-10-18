package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.println("* -c OR --config - JSON config file   *");
            System.out.println("---------------------------------------");

        } else if(args[0].equals("--input") || args[0].equals("-i")){

            //remove the option and store as file name by removing the first element of the argument array
            String[] fileNameFull = new String[args.length-1];
            for(int i=1;i<args.length;i++){
                fileNameFull[i-1] = args[i];
            }

            processInput(fileNameReader(fileNameFull), "dist");

        } else if(args[0].equals("--config") || args[0].equals("-c")) {
            updateConfiguration(args);
        } else {
            System.out.println("Invalid argument passed. Please pass a valid argument");
        }

    }

    private static void updateConfiguration(String[] args) {
        //parses JSON file for input and output values
        JSONParser parser = new JSONParser();

        try {
            Object configObject = parser.parse(new FileReader(args[1]));
            JSONObject configJsonObject = (JSONObject) configObject;

            String input = "";
            String output = "";

            if ((String) configJsonObject.get("input") == null) {
                throw new Exception("Invalid input file/folder passed. Please pass a valid input");
            } else {
                input = (String) configJsonObject.get("input");
            }

            if ((String) configJsonObject.get("output") == null) {
                output = "dist";
            } else {
                output = (String) configJsonObject.get("output");
            }

            processInput(input, output);

        } catch (FileNotFoundException ex) {
            System.out.println("The config file could not be found");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() == null ? "The config file could not be parsed" : ex.getMessage());
        }

    }

    private static void processInput (String inputFile, String output) {
        if(inputFile.endsWith(".txt")){
            //if input file is only one file
            File file = new File(inputFile);
            //create html
            TextUtils.createHtmlFromTxt(file, output);

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
                for(File file :allFiles){
                    TextUtils.createHtmlFromTxt(file, output);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
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




}
