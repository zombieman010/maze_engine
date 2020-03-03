package com.nathanielpautzke.mazeengine.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nathanielpautzke.mazeengine.maze.Graph;
import com.nathanielpautzke.mazeengine.maze.Node;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BFS {

    private MazeOutput mazeOutput;

    public BFS(MazeOutput mazeOutput){
        this.mazeOutput = mazeOutput;
    }

    public MazeOutput solveMaze(Graph graph) throws JsonProcessingException {

        Queue<Node> queue = new LinkedList<>();

        String start = graph.getPosition().get("start");
        Node startNode = graph.getNodeMap().get(start);

        Map<String, Node> nodeMap = graph.getNodeMap();

        queue.add(startNode);

        while (!queue.isEmpty()) {

            Node nodeList = queue.peek();

            if(nodeList.isEnd()){
                break;
            }

            if (nodeList != null) {
                for (String edge : nodeList.getEdges()) {

                    Node edgeNOde = nodeMap.get(edge);
                    if(edgeNOde != null) {
                        if(edgeNOde.getParent() == null){
                            edgeNOde.setParent(nodeList.getNodeLocation());
                        }
                    }

                    if(!queue.contains(nodeMap.get(edge))){
                        queue.add(nodeMap.get(edge));
                    }
                }
                queue.remove();
            }
        }

        Node end = nodeMap.get(graph.getPosition().get("end"));
        List<String> path = new ArrayList<>();
        String tmp = "";
        for (int i = 0; i < nodeMap.size(); i++){

            if(i == 0) {
                path.add(end.getParent());
                tmp = end.getParent();
            }else {
                tmp = nodeMap.get(tmp).getParent();
                if (nodeMap.get(tmp).isStart()) {
                    break;
                } else {
                    if (!path.contains(tmp)) {
                        path.add(tmp);
                    }
                }
            }
        }

        return mazeOutput.outputGraph(graph.getMazeTable(), path);
    }
}
