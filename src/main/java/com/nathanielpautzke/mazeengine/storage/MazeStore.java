package com.nathanielpautzke.mazeengine.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MazeStore extends JpaRepository<Maze, Long> {

}
