package com.maxmatveev.maze.service;

import com.maxmatveev.maze.MazeLoadException;
import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeExplorer;
import com.maxmatveev.maze.model.MazeSolution;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MazeExplorationResolverTest {
    @Test
    public void testUnsolvableResolvedToNull() {
        assertNull(new MazeExplorationResolver().resolveSolution(new MazeSolution(
                new Maze(10, 10),
                false,
                null)));
    }

    @Test
    public void testStraightMoveResolved() throws IOException, MazeLoadException {
        Maze maze = new MazeLoader().loadMaze(TempFileHelper.createTempFile("S  F"));
        MazeSolution solution = new MazeSolution(maze, true, Arrays.asList(maze.getCell(0, 0), maze.getCell(1, 0), maze.getCell(2, 0), maze.getCell(3, 0)));
        MazeExplorer explorer = new MazeExplorationResolver().resolveSolution(solution);
        assertEquals(Arrays.asList(MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.MOVE_FORWARD), explorer.getActions());
        assertEquals(MazeExplorer.Direction.RIGHT, explorer.getInitialDirection());
    }

    @Test
    public void testMazeSolutionResolved() throws IOException, MazeLoadException {
        Maze maze = new MazeLoader().loadMaze(TempFileHelper.createTempFile("XFX\nX  \nSX \n   "));
        MazeSolution solution = new MazeSolution(maze, true, Arrays.asList(maze.getCell(0, 2), maze.getCell(0, 3), maze.getCell(1, 3), maze.getCell(2, 3), maze.getCell(2, 2), maze.getCell(2, 1), maze.getCell(1, 1), maze.getCell(1, 0)));
        MazeExplorer explorer = new MazeExplorationResolver().resolveSolution(solution);
        assertEquals(Arrays.asList(MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.TURN_LEFT, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.TURN_LEFT, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.TURN_LEFT, MazeExplorer.Action.MOVE_FORWARD, MazeExplorer.Action.TURN_RIGHT, MazeExplorer.Action.MOVE_FORWARD), explorer.getActions());
        assertEquals(MazeExplorer.Direction.DOWN, explorer.getInitialDirection());
    }
}