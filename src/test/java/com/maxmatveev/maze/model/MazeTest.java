package com.maxmatveev.maze.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {
    @Test
    public void testBasicProperties() {
        Maze maze = new Maze(2, 3);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        maze.setCell(new MazeCell(0, 1, MazeCell.Type.WALL));
        maze.setCell(new MazeCell(1, 1, MazeCell.Type.NORMAL));
        maze.setCell(new MazeCell(1, 2, MazeCell.Type.FINISH));
        assertTrue(maze.isValid());
        assertEquals(2, maze.getWidth());
        assertEquals(3, maze.getHeight());
        assertEquals(new MazeCell(0, 0, MazeCell.Type.START), maze.getStart());
        assertEquals(new MazeCell(1, 2, MazeCell.Type.FINISH), maze.getFinish());
        assertArrayEquals(new MazeCell[][] {
                new MazeCell[] {
                        new MazeCell(0, 0, MazeCell.Type.START), null
                },
                new MazeCell[] {
                        new MazeCell(0, 1, MazeCell.Type.WALL), new MazeCell(1, 1, MazeCell.Type.NORMAL)
                },
                new MazeCell[] {
                        null, new MazeCell(1, 2, MazeCell.Type.FINISH)
                }
        }, maze.getData());
        assertEquals(new MazeCell(1, 1, MazeCell.Type.NORMAL), maze.getCell(1, 1));
    }

    @Test(expected = IllegalStateException.class)
    public void testTwoStartsFailed() {
        Maze maze = new Maze(1, 2);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        maze.setCell(new MazeCell(0, 1, MazeCell.Type.START));
   }

    @Test(expected = IllegalStateException.class)
    public void testTwoFinishesFailed() {
        Maze maze = new Maze(2, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.FINISH));
        maze.setCell(new MazeCell(1, 0, MazeCell.Type.FINISH));
   }

    @Test
    public void testNoStartFailed() {
        Maze maze = new Maze(1, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.FINISH));
        assertFalse(maze.isValid());
    }

    @Test
    public void testNoFinishFailed() {
        Maze maze = new Maze(1, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        assertFalse(maze.isValid());
    }

    @Test
    public void testStartEqualsFinishFailed() {
        Maze maze = new Maze(1, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.FINISH));
        assertFalse(maze.isValid());
    }

    @Test
    public void testStartRemovedFailed() {
        Maze maze = new Maze(2, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        maze.setCell(new MazeCell(1, 0, MazeCell.Type.FINISH));
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.NORMAL));
        assertFalse(maze.isValid());
    }

    @Test
    public void testFinishRemovedFailed() {
        Maze maze = new Maze(2, 1);
        maze.setCell(new MazeCell(0, 0, MazeCell.Type.START));
        maze.setCell(new MazeCell(1, 0, MazeCell.Type.FINISH));
        maze.setCell(new MazeCell(1, 0, MazeCell.Type.NORMAL));
        assertFalse(maze.isValid());
    }
}