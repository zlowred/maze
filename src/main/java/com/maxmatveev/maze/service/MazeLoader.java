package com.maxmatveev.maze.service;

import com.maxmatveev.maze.MazeLoadException;
import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeCell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeLoader {
    public Maze loadMaze(File input) throws MazeLoadException {
        try (Stream<String> lines = Files.lines(input.toPath())) {
            String mazeData[] = lines.toArray(String[]::new);
            if (mazeData.length == 0 || mazeData[0].length() == 0) {
                throw new MazeLoadException("Can't load maze: input file should contain at least one line with at least one character");
            }
            Maze maze = new Maze(mazeData[0].length(), mazeData.length);
            for (int y = 0; y < maze.getHeight(); y++) {
                if (mazeData[y].length() != maze.getWidth()) {
                    throw new MazeLoadException("Can't load maze: input file contains lines of different length");
                }
                for (int x = 0; x < maze.getWidth(); x++) {
                    MazeCell.Type type;
                    switch (mazeData[y].charAt(x)) {
                        case ' ':
                            type = MazeCell.Type.NORMAL;
                            break;
                        case 'S':
                            type = MazeCell.Type.START;
                            break;
                        case 'F':
                            type = MazeCell.Type.FINISH;
                            break;
                        case 'X':
                            type = MazeCell.Type.WALL;
                            break;
                        default:
                            throw new MazeLoadException("Can't load maze: wrong character detected; allowed characters ['X', 'S', 'F', ' ']");
                    }
                    MazeCell cell = new MazeCell(x, y, type);
                    maze.setCell(cell);
                }
            }
            if (!maze.isValid()) {
                throw new MazeLoadException("Maze was loaded but did not pass validation");
            }
            return maze;
        } catch (IOException | IllegalStateException e) {
            throw new MazeLoadException("Can't load maze", e);
        }
    }
}
