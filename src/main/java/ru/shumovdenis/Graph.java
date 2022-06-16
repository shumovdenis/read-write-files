package ru.shumovdenis;

import java.util.Map;
import java.util.*;

public class Graph {

    private HashMap<String, List<String>> vertexMap = new HashMap<String, List<String>>();

    public void addVertex(String vertexName) {
        if (!hasVertex(vertexName)) {
            vertexMap.put(vertexName, new ArrayList<String>());
        }
    }

    public boolean hasVertex(String vertexName) {
        return vertexMap.containsKey(vertexName);
    }

    public void addEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) addVertex(vertexName1);
        if (!hasVertex(vertexName2)) addVertex(vertexName2);
        List<String> edges1 = vertexMap.get(vertexName1);
        edges1.add(vertexName2);
        Collections.sort(edges1);
    }

    public Map<String, List<String>> getVertexMap() {
        return vertexMap;
    }
}
