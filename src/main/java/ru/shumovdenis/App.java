package ru.shumovdenis;

import java.io.File;
import java.util.*;

public class App {

    private static final String SEP = File.separator;
    private static String resultFileName = "result.txt";
    private static String resultPath = "result" + SEP;
    private static String rootDir = "Files";

    static Graph graph;
    // мапа посещенных вершин
    static Map<String, Mark> visitedMap = new LinkedHashMap<String, Mark>();
    // счетчик
    static int counter = 1;

    public static void main(String[] args) {

        graph = new Graph();
        makeGraph(new File(rootDir), graph);

        Map<String, List<String>> vm = graph.getVertexMap();

        List<String> vertexList = new ArrayList<String>(vm.size());
        vertexList.addAll(vm.keySet());
        Collections.sort(vertexList);

        for (String v : vertexList) {
            dfs(v);
        }

        for (Map.Entry<String, Mark> entry : visitedMap.entrySet()) {
            Mark m = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), m.pre, m.post);
        }
    }

    // поиск в глуюбину
    static void dfs(String vertexName) {
        if (visitedMap.containsKey(vertexName)) return;

        // время входа
        visitedMap.put(vertexName, new Mark(counter, -1));
        counter++;

        // извлечение смежных вершин
        Map<String, List<String>> vm = graph.getVertexMap();
        List<String> adjacentVertices = vm.get(vertexName);

        for (String v : adjacentVertices) {
            if (visitedMap.containsKey(v)) continue;
            dfs(v);
        }

        // время выхода
        Mark m = visitedMap.get(vertexName);
        m.post = counter++;
    }

    static void makeGraph(File file, Graph graph) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                graph.addEdge(child.getName(), file.getName());
                if (!child.isDirectory()) {
                    graph.addEdge(child.getName(), file.getName());
                }
                for (File item : file.listFiles()) {
                    graph.addEdge(child.getName(), file.getName());
                    if (item.isDirectory()) {
                        for (File txtFiles : item.listFiles()) {
                            if (!txtFiles.isDirectory()) {
                                graph.addEdge(item.getName(), txtFiles.getName());
                            }
                        }
                    }
                }
            }
        }
    }

}

