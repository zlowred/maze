package com.maxmatveev.maze.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeSolution {
    private Maze maze;
    private boolean solvable;
    private List<MazeCell> optimalPath;

    public MazeSolution(Maze maze, boolean solvable, List<MazeCell> optimalPath) {
        this.maze = maze;
        this.solvable = solvable;
        this.optimalPath = optimalPath;
    }

    public Maze getMaze() {
        return maze;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public List<MazeCell> getOptimalPath() {
        return optimalPath == null ? null : Collections.unmodifiableList(optimalPath);
    }
}
