package com.maxmatveev.maze.service;

import com.maxmatveev.maze.MazeLoadException;
import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeCell;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MazeLoaderTest {
    @Test(expected = MazeLoadException.class)
    public void testLoadingEmptyFileFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile(""));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingNonRectangularFileFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("XXX\nXXXX\nXXX"));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingFileWithWrongCharactersFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("XXX\nXaX\nXXX"));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingFileWithoutStartFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("F"));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingFileWithoutFinishFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("XXX\nXSX\nXXX"));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingFileWithMultipleStartsFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("XXX\nXFX\nSSX"));
    }

    @Test(expected = MazeLoadException.class)
    public void testLoadingFileWithMultipleFinishesFails() throws IOException, MazeLoadException {
        new MazeLoader().loadMaze(TempFileHelper.createTempFile("FFF\nXSX\nXXX"));
    }

    @Test
    public void testMinimalMazeLoads1() throws IOException, MazeLoadException {
        assertNotNull(new MazeLoader().loadMaze(TempFileHelper.createTempFile("SF")));
    }

    @Test
    public void testMinimalMazeLoads2() throws IOException, MazeLoadException {
        assertNotNull(new MazeLoader().loadMaze(TempFileHelper.createTempFile("F\nS")));
    }

    @Test
    public void testBasicMazeLoading() throws IOException, MazeLoadException {
        Maze maze = new MazeLoader().loadMaze(TempFileHelper.createTempFile("XXXX\nX SX\nXF X\nXXXX\nXXXX"));
        assertNotNull(maze);
        assertEquals(4, maze.getWidth());
        assertEquals(5, maze.getHeight());
        assertEquals(MazeCell.Type.FINISH, maze.getCell(1, 2).getType());
        assertEquals(MazeCell.Type.START, maze.getCell(2, 1).getType());
        assertEquals(MazeCell.Type.WALL, maze.getCell(0, 0).getType());
        assertEquals(MazeCell.Type.NORMAL, maze.getCell(1, 1).getType());
        assertTrue(maze.isValid());
    }
}