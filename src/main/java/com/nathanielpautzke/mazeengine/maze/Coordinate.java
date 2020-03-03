package com.nathanielpautzke.mazeengine.maze;

import lombok.Data;

@Data
public class Coordinate {

    private int xCoordinate;
    private int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
