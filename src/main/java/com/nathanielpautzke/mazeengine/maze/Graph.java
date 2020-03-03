package com.nathanielpautzke.mazeengine.maze;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class Graph {

    private Map<String, Node> nodeMap;
    private Map<String, String> position;
    private List<String[]> mazeTable;

    public Graph createGraph(List<String[]> mazeTable) {
        Map<String, Node> nodeMap = new HashMap<>();
        Map<String, String> position = new HashMap<>();

        int yCoordinate = 0;
        for (String[] item : mazeTable) {
            for (int i = 0; i < item.length - 1; i++) {

                if (!item[i].equals("#")) {

                    String currentRow = String.valueOf(i);
                    String currentColumn = String.valueOf(yCoordinate);

                    if (yCoordinate == 0 || yCoordinate == mazeTable.size() - 1) {

                        List<String> edges = new ArrayList<>();

                        boolean isStart = false;
                        boolean isEnd = false;


                        if (item[i].equals("A")) {
                            isStart = true;
                            position.put("start", currentRow + "," + currentColumn);
                        }

                        if (item[i].equals("B")) {
                            isEnd = true;
                            position.put("end", currentRow + "," + currentColumn);;
                        }

                        if (yCoordinate != 0) {

                            //North
                            if (!mazeTable.get(yCoordinate - 1)[i].equals("#")) {
                                addEdge(edges, i, yCoordinate - 1);

                            }

                        } else {
                            //South
                            if (!mazeTable.get(yCoordinate + 1)[i].equals("#")) {
                                addEdge(edges, i, yCoordinate + 1);
                            }
                        }

                        if (i != 0) {
                            //West
                            if (!mazeTable.get(yCoordinate)[i - 1].equals("#")) {
                                addEdge(edges, i - 1, yCoordinate);
                            }

                        } else {
                            //East
                            if (!mazeTable.get(yCoordinate)[i + 1].equals("#")) {
                                addEdge(edges, i + 1, yCoordinate);
                            }
                        }

                        Node current = new Node(currentRow + "," + currentColumn, edges, isStart, isEnd, null);
                        nodeMap.put(currentRow + "," + currentColumn, current);
                    }

                    if (yCoordinate > 0 && yCoordinate < mazeTable.size() - 1) {
                        if (i > 0 && i < item.length - 1) {

                            List<String> edges = new ArrayList<>();

                            boolean isStart = false;
                            boolean isEnd = false;

                            if (item[i].equals("A")) {
                                isStart = true;
                                position.put("start", currentRow + "," + currentColumn);
                            }

                            if (item[i].equals("B")) {
                                isEnd = true;
                                position.put("end", currentRow + "," + currentColumn);
                            }

                            //North
                            if (!mazeTable.get(yCoordinate - 1)[i].equals("#")) {
                                addEdge(edges, i, yCoordinate - 1);

                            }

                            //East
                            if (!mazeTable.get(yCoordinate)[i + 1].equals("#")) {
                                addEdge(edges, i + 1, yCoordinate);
                            }

                            //South
                            if (!mazeTable.get(yCoordinate + 1)[i].equals("#")) {
                                addEdge(edges, i, yCoordinate + 1);
                            }

                            //West
                            if (!mazeTable.get(yCoordinate)[i - 1].equals("#")) {
                                addEdge(edges, i - 1, yCoordinate);
                            }

                            Node current = new Node(currentRow + "," + currentColumn, edges, isStart, isEnd, null);
                            nodeMap.put(currentRow + "," + currentColumn, current);
                        }
                    }
                }
            }
            yCoordinate++;
        }

        Graph mazeGraph =  new Graph();
        mazeGraph.setNodeMap(nodeMap);
        mazeGraph.setPosition(position);
        mazeGraph.setMazeTable(mazeTable);

        return mazeGraph;
    }

    private void addEdge(List<String> edges, int xCoord, int yCoord){
        String row = String.valueOf(xCoord);
        String column = String.valueOf(yCoord);
        edges.add(row + "," + column);
    }
}
