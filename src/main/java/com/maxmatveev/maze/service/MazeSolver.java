package com.maxmatveev.maze.service;

import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeCell;
import com.maxmatveev.maze.model.MazeSolution;

import java.util.*;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeSolver {
    public MazeSolution solveMaze(Maze maze) {
        Queue<MazeCell> unvisited = new PriorityQueue<>();
        Arrays.stream(maze.getData()).forEach(line -> Arrays.stream(line)
                .filter(cell -> !MazeCell.Type.WALL.equals(cell.getType()))
                .forEach(unvisited::add));

        while (!unvisited.isEmpty()) {
            MazeCell current = unvisited.poll();
            current.setVisited(true);
            checkNeighbour(maze, unvisited, current, 1, 0);
            checkNeighbour(maze, unvisited, current, -1, 0);
            checkNeighbour(maze, unvisited, current, 0, 1);
            checkNeighbour(maze, unvisited, current, 0, -1);
        }
        if (maze.getFinish().getDistance() == MazeCell.DISTANCE_UNREACHABLE) {
            return new MazeSolution(maze, false, null);
        }

        List<MazeCell> path = new LinkedList<>();

        for (MazeCell current = maze.getFinish(); current != null; current = current.getPrevious()) {
            path.add(0, current);
        }
        return new MazeSolution(maze, true, path);
    }

    private void checkNeighbour(Maze maze, Queue<MazeCell> unvisited, MazeCell current, int dx, int dy) {
        MazeCell next;
        if (current.getX() + dx < 0 || current.getX() + dx >= maze.getWidth() ||
                current.getY() + dy < 0 || current.getY() + dy >= maze.getHeight()) {
            return;
        }
        if ((next = maze.getCell(current.getX() + dx, current.getY() + dy)) != null
                && !MazeCell.Type.WALL.equals(next.getType())
                && !next.isVisited()) {
            if (next.getDistance() > current.getDistance() + 1) {
                unvisited.remove(next);
                next.setDistance(current.getDistance() + 1);
                next.setPrevious(current);
                unvisited.add(next);
            }
        }
    }
}
