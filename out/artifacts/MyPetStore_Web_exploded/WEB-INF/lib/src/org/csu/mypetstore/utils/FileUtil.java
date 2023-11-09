package org.csu.mypetstore.utils;

import java.io.*;

public class FileUtil {
    public static String FileToString(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
