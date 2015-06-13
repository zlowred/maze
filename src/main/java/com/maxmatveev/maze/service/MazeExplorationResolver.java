package com.maxmatveev.maze.service;

import com.maxmatveev.maze.model.MazeCell;
import com.maxmatveev.maze.model.MazeExplorer;
import com.maxmatveev.maze.model.MazeSolution;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeExplorationResolver {
    public MazeExplorer resolveSolution(MazeSolution solution) {
        if (!solution.isSolvable()) {
            return null;
        }
        MazeExplorer explorer = null;
        MazeCell current = null;
        MazeExplorer.Direction initialDirection = null;
        MazeExplorer.Direction currentDirection = null;
        for (MazeCell next : solution.getOptimalPath()) {
            if (current == null) {
                current = next;
                continue;
            }

            MazeExplorer.Direction newDirection = getNewDirection(current, next);

            if (initialDirection == null) {
                initialDirection = newDirection;
                currentDirection = newDirection;
                explorer = new MazeExplorer(solution.getMaze(), initialDirection);
            }

            doMoves(explorer, currentDirection, newDirection);

            currentDirection = newDirection;
            current = next;
        }

        return explorer;
    }

    private void doMoves(MazeExplorer explorer, MazeExplorer.Direction currentDirection, MazeExplorer.Direction newDirection) {
        if (newDirection != currentDirection) {
            int currentDirectionIndex = MazeExplorer.DIRECTION_ORDER.indexOf(currentDirection);
            if (MazeExplorer.DIRECTION_ORDER.get((currentDirectionIndex + 1) % MazeExplorer.DIRECTION_ORDER.size()).equals(newDirection)) {
                explorer.turnRight();
            } else {
                explorer.turnLeft();
            }
        }
        explorer.moveForward();
    }

    private MazeExplorer.Direction getNewDirection(MazeCell current, MazeCell next) {
        MazeExplorer.Direction newDirection = null;
        if (next.getX() < current.getX()) {
            newDirection = MazeExplorer.Direction.LEFT;
        } else if (next.getX() > current.getX()) {
            newDirection = MazeExplorer.Direction.RIGHT;
        } else if (next.getY() < current.getY()) {
            newDirection = MazeExplorer.Direction.UP;
        } else if (next.getY() > current.getY()) {
            newDirection = MazeExplorer.Direction.DOWN;
        }
        return newDirection;
    }
}
