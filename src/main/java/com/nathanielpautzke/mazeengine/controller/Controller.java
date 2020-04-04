package com.nathanielpautzke.mazeengine.controller;

import com.nathanielpautzke.mazeengine.engine.Engine;
import com.nathanielpautzke.mazeengine.engine.MazeOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    private Engine engine;

    public Controller(Engine engine){
        this.engine = engine;
    }

    @PostMapping(path = "/api/v1/solve")
    public ResponseEntity<Object> endpoint(@RequestParam("file") MultipartFile file)  {

        try{
            log.info("Started solving maze.");
            MazeOutput mazeOutput = engine.processMaze(file);
            return ResponseEntity.ok(mazeOutput);
        } catch (Exception e) {
            log.error("There was an error in the maze solve");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
