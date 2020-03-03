package com.nathanielpautzke.mazeengine.storage;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "maze_store")
@Data
public class Maze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID mazeId;

    @Lob
    private byte[] file;

    private String fileName;
    private int nodes;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private long duration;
}
