package ru.shumovdenis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class App {

    private static final String SEP = File.separator;
    private static String resultFileName = "result.txt";
    private static String resultPath = "result" + SEP;

    public static void main(String[] args) {

        HashMap<String, String> filesMap = new HashMap<>();
        File dir = new File("Files");

        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    for (File txtFiles : item.listFiles()) {
                        filesMap.put(txtFiles.getName(), txtFiles.getPath());
                    }
                } else {
                    filesMap.put(item.getName(), item.getPath());
                }
            }
        }

        TreeMap<String, String> sortedMap = new TreeMap<>(filesMap);
        sortedMap.entrySet();

        File resultDir = new File(resultPath);
        if (!resultDir.exists()) {
            resultDir.mkdir();
        }

        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            String filePath = entry.getValue();
            writeInResult(filePath);
        }
    }

    public static void writeInResult(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(resultPath + resultFileName, true))) {
            String s;
            while ((s = br.readLine()) != null) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка во время копирования " + e.getMessage());
        }
    }
}

