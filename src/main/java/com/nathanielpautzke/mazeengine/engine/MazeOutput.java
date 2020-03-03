package com.nathanielpautzke.mazeengine.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nathanielpautzke.mazeengine.maze.Coordinate;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class MazeOutput {

    private List<String> outputMaze;
    private int nodes;

    public MazeOutput outputGraph(List<String[]> mazeTable, List<String> path) throws JsonProcessingException {

        for(String coord : path) {

            Coordinate coordinate = parseCoord(coord);
            mazeTable.get(coordinate.getYCoordinate())[coordinate.getXCoordinate()] = "@";
        }

        List<String> mazeToString = new ArrayList<>();
        for(String[] row : mazeTable) {
            mazeToString.add(String.join("", row));
        }

        MazeOutput output = new MazeOutput();
        output.setOutputMaze(mazeToString);
        output.setNodes(path.size());

        return output;

    }
    private Coordinate parseCoord(String cood) {
        String[] coord = cood.split(",");
        return new Coordinate(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
    }
}
