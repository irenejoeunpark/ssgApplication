//package com.company;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class MDUtilsArc {
//
////    public static void createHTMLFromMd(File fileName) {
////        try{
////            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
////            String aLine;
////
////            String fName = fileName.toString().split("\\.")[0];
////
////            //only getting the file name from the entire file address
////            String[] getOnlyName = fName.split("\\\\");
////            String title = getOnlyName[getOnlyName.length-1];
////
////
////            // Complete HTML file
////            String htmlS = "<!doctype html>\r\n"
////                    + "<html lang=\"en\">\r\n"
////                    + "<head>\r\n"
////                    + "  <meta charset=\"utf-8\">\r\n"
////                    + "  <title>"
////                    + title
////                    + "</title>\r\n"
////                    + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
////                    + "</head>\r\n"
////                    + "<body>\r\n";
////            String htmlE = "</body>\r\n"
////                    + "</html>\r\n"
////                    + "";
////
////            //create directory if not exist
////            new File("dist").mkdir();
////
////            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(title),"UTF-8"));
////            writer.write(htmlS);
////            aLine = reader.readLine();
////
////            while((aLine=reader.readLine()) != null){
////                aLine = aLine.trim();
////
////                if(aLine.length() == 0){
////                    writer.newLine();
////                } else if(aLine.startsWith("```")){
////                    writer.write("<code>");
////                    writer.write(aLine);
////                    writer.write("</code>");
////
////                } else if(aLine.startsWith("# ")){
////                    writer.write("<h1>");
////                    writer.write(aLine);
////                    writer.write("</h1>");
////
////                } else {
////                    writer.write("<p>");
////                    writer.write(aLine);
////                    writer.write("</p>");
////                }
////            }
////
////            writer.write(htmlE);
////            writer.close();
////        }catch(IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//
//    public static void createHTMLFromMd(File file) throws IOException {
//        String convertedText = MDUtilsArc.readFile(file);
//
//        String title = convertedText.split("<br/>")[0].replace("# ","");
//
//        String body = MDUtilsArc.getBodyFromText(convertedText.split("<br/>"));
//        body = DoubleAsteriskMarkdownParser.parseMarkdownLine(body.split("<br/>"));
//
//        // Complete HTML file
//        String html = "<!doctype html>\r\n"
//                + "<html lang=\"en\">\r\n"
//                + "<head>\r\n"
//                + "  <meta charset=\"utf-8\">\r\n"
//                + "  <title>"
//                + title
//                + "</title>\r\n"
//                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
//                + "</head>\r\n"
//                + "<body>\r\n"
//                + body
//                + "</body>\r\n"
//                + "</html>\r\n"
//                + "";
//
//        // write File
//        String htmlFileName = "dist\\" + title + ".html";
//
//        Path path = Paths.get(htmlFileName);
//
//        byte[] strToBytes = html.getBytes();
//
//        Files.write(path, strToBytes);
//
//    }
//
//    public static String readFile(File file){
//        StringBuilder sb = new StringBuilder();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                if (line.equals("")) {
//                    sb.append("<br/>");
//                } else {
//                    sb.append(line).append(" ");
//                }
//            }
//
//            br.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
//
//    public static String getBodyFromText(String[] lines){
//        StringBuilder sb = new StringBuilder();
//        Boolean inCodeBlock = false;
//
//        for(int i = 0; i < lines.length; i++){
//            System.out.println(lines[i]);
//            if(lines[i].length()==0){
//                sb.append("<br/>");
//            } else {
//                if(lines[i].startsWith("# ")){
//                    sb.append("<h1>").append(lines[i].replace("# ","")).append("</h1>").append("<br/>");
//                } else if (lines[i].startsWith("```")){
//                    sb.append("<p><code>").append(lines[i].replace("```","")).append("</code></p>");
//                } else{
//                    sb.append("<p>").append(lines[i]).append("</p>").append("<br/>");
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static Boolean isCodeBlockTag(String line){
//        return line.startsWith("```");
//    }
//
//}
