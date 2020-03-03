package com.nathanielpautzke.mazeengine.maze;

import lombok.Data;

import java.util.List;

@Data
public class Node {

    private String nodeLocation;
    private List<String> edges;
    private boolean isStart;
    private boolean isEnd;
    private String parent;

    public Node(String nodeLocation, List<String> edges, boolean isStart, boolean isEnd, String parent) {
        this.nodeLocation = nodeLocation;
        this.edges = edges;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.parent = parent;
    }
}
