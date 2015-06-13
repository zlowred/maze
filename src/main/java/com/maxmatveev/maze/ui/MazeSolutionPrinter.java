package com.maxmatveev.maze.ui;

import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeCell;
import com.maxmatveev.maze.model.MazeSolution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeSolutionPrinter {
    private MazeRepresentation representation;

    public MazeSolutionPrinter(MazeRepresentation representation) {
        this.representation = representation;
    }

    public String getMazeRepresentation(Maze maze) {
        return getMazePathRepresentation(maze, null);
    }

    public String getSolutionRepresentation(MazeSolution solution) {
        StringBuilder output = new StringBuilder();
        output.append("Initial maze:\n");
        output.append(getMazeRepresentation(solution.getMaze()));
        if (solution.isSolvable()) {
            output.append(String.format("\nMaze is solvable in %d steps (not counting turns):\n", solution.getOptimalPath().size() - 1));
            output.append(getMazePathRepresentation(solution.getMaze(), new HashSet<>(solution.getOptimalPath())));
        } else {
            output.append("\nMaze is NOT solvable\n");
        }
        return output.toString();
    }

    public String getMazePathRepresentation(Maze maze, Set<MazeCell> pathLookup) {
        StringBuilder output = new StringBuilder();
        Arrays.stream(maze.getData()).forEach(line -> {
            Arrays.stream(line).forEach(cell ->
                    output.append(
                            pathLookup != null && pathLookup.contains(cell) ?
                                    representation.characterForPath() :
                                    representation.characterForCellType(cell.getType())));
            output.append('\n');
        });
        return output.toString();
    }
}
