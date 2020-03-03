package com.nathanielpautzke.mazeengine.engine;

import com.nathanielpautzke.mazeengine.maze.Graph;
import com.nathanielpautzke.mazeengine.storage.Maze;
import com.nathanielpautzke.mazeengine.storage.MazeStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class Engine {

    private BFS bfs;
    private FileParser fileParser;
    private MazeOutput mazeOutput;
    private MazeStoreService mazeStoreService;

    public Engine(BFS bfs, FileParser fileParser, MazeOutput mazeOutput, MazeStoreService mazeStoreService) {
        this.bfs = bfs;
        this.fileParser = fileParser;
        this.mazeOutput = mazeOutput;
        this.mazeStoreService = mazeStoreService;
    }

    public MazeOutput processMaze(MultipartFile file) throws IOException {
        UUID mazeID = UUID.randomUUID();

        LocalDateTime start = LocalDateTime.now();

        List<String[]> maze = fileParser.parseFile(file);
        Graph graph = new Graph().createGraph(maze);
        MazeOutput output =  bfs.solveMaze(graph);

        LocalDateTime end = LocalDateTime.now();


        long elapsedSeconds = Duration.between(start, end).toSeconds();
        log.info("Maze solved in {} seconds.", elapsedSeconds);

        Maze storeMaze = new Maze();
        storeMaze.setMazeId(mazeID);
        storeMaze.setFileName(file.getOriginalFilename());
        storeMaze.setFile(file.getBytes());
        storeMaze.setNodes(output.getNodes());
        storeMaze.setStartTime(start);
        storeMaze.setEndTime(end);
        storeMaze.setDuration(elapsedSeconds);

        log.info("Maze info stored in database.");
        mazeStoreService.createMazeStore(storeMaze);

        return output;
    }
}
