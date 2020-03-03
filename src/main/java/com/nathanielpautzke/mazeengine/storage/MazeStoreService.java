package com.nathanielpautzke.mazeengine.storage;

import org.springframework.stereotype.Service;

@Service
public class MazeStoreService {

    private MazeStore mazeStore;

    public MazeStoreService(MazeStore mazeStore) {
        this.mazeStore = mazeStore;
    }

    public void createMazeStore(Maze maze) {
        mazeStore.save(maze);
    }
}
