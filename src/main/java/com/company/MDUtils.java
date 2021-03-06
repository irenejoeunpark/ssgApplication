package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.company.HTMLBuilder.writeHtmlHeader;
import static com.company.HTMLBuilder.writeHtmlFoot;

public class MDUtils {

    public static void createHTMLFromMd(File file) throws IOException {

        String convertedText = MDUtils.readFile(file);

        String br = "<br/>";
        String title = convertedText.split(br)[0].replace("# ", "");

        String body = MDUtils.getBodyFromText(convertedText.split(br));
        body = DoubleAsteriskMarkdownParser.parseMarkdownLine(body.split(br));

        // write File
        String htmlFileName = "dist\\" + title + ".html";

        Path path = Paths.get(htmlFileName);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName), "UTF-8"));
        writeHtmlHeader(writer, path.toString());
        writer.write(body);

        writeHtmlFoot(writer);
        writer.close();

    }

    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    sb.append("<br/>");
                } else {
                    sb.append(line).append(" ");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getBodyFromText(String[] lines) {
        StringBuilder sb = new StringBuilder();
        String br = "<br/>";

        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
            if (lines[i].length() == 0) {
                sb.append(br);
            } else {
                if (lines[i].startsWith("# ")) {
                    sb.append("<h1>").append(lines[i].replace("# ", "")).append("</h1>").append(br);
                } else if (lines[i].startsWith("```")) {
                    sb.append("<p><code>").append(lines[i].replace("```", "")).append("</code></p>").append(br);
                } else if (lines[i].startsWith("*")) {
                    sb.append("<p><strong>").append(lines[i].replace("*", "")).append("</strong></p>").append(br);
                } else if (lines[i].startsWith("----")) {
                    sb.append("<hr>").append(br);
                } else {
                    sb.append("<p>").append(lines[i]).append("</p>").append(br);
                }
            }
        }
        return sb.toString();
    }


}
