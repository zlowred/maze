package com.maxmatveev.maze.service;

import com.maxmatveev.maze.MazeLoadException;
import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeSolution;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MazeSolverTest {
    @Test
    public void testUnsolvableFails() throws IOException, MazeLoadException {
        Maze maze = new MazeLoader().loadMaze(TempFileHelper.createTempFile("SXF"));
        MazeSolution solution = new MazeSolver().solveMaze(maze);
        assertFalse(solution.isSolvable());
        assertEquals(maze, solution.getMaze());
        assertNull(solution.getOptimalPath());
    }

    @Test
    public void testSolvableOk() throws IOException, MazeLoadException {
        Maze maze = new MazeLoader().loadMaze(TempFileHelper.createTempFile("XFX\nX  \nSX \n   "));
        MazeSolution solution = new MazeSolver().solveMaze(maze);
        assertTrue(solution.isSolvable());
        assertEquals(maze, solution.getMaze());
        assertEquals(Arrays.asList(maze.getCell(0, 2), maze.getCell(0, 3), maze.getCell(1, 3), maze.getCell(2, 3), maze.getCell(2, 2), maze.getCell(2, 1), maze.getCell(1, 1), maze.getCell(1, 0)), solution.getOptimalPath());
    }
}