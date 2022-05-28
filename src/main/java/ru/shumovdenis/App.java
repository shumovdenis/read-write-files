package ru.shumovdenis;

import java.io.*;
import java.util.*;

public class App {

    private static final String SEP = File.separator;
    private static String resultFileName = "result.txt";
    private static String resultPath = "result" + SEP;
    private static String rootDir = "Files";

    public static void main(String[] args) {

        HashMap<String, String> filesMap = new HashMap<>();

        Collection<File> all = new ArrayList<File>();
        addTree(new File("rootDir"), all, filesMap);
        System.out.println(all);
        System.out.println(filesMap);

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

    static void addTree(File file, Collection<File> all, HashMap<String, String> filesMap) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (!child.isDirectory()) filesMap.put(child.getName(), child.getPath());
                for (File item : file.listFiles()) {
                    if (item.isDirectory()) {
                        for (File txtFiles : item.listFiles()) {
                            if (!txtFiles.isDirectory()) {
                                filesMap.put(txtFiles.getName(), txtFiles.getPath());
                            }
                        }
                    }
                }
                all.add(child);
                addTree(child, all, filesMap);
            }
        }
    }
}

