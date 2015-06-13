package com.maxmatveev.maze.model;

import java.util.*;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeExplorer {
    public static final List<Direction> DIRECTION_ORDER =
            Collections.unmodifiableList(
                    Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT));
    private Direction initialDirection;
    private Direction currentDirection;
    private List<Action> actions = new ArrayList<>();
    private int x, y;
    private Maze maze;
    public MazeExplorer(Maze maze, Direction initialDirection) {
        this.maze = maze;
        x = maze.getStart().getX();
        y = maze.getStart().getY();
        this.initialDirection = initialDirection;
        currentDirection = initialDirection;
    }

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public Collection<MazeCell> getAvailableMoves() {
        List<MazeCell> allowed = new ArrayList<>();
        addTargetIfAllowed(allowed, 1, 0);
        addTargetIfAllowed(allowed, -1, 0);
        addTargetIfAllowed(allowed, 0, 1);
        addTargetIfAllowed(allowed, 0, -1);
        return Collections.unmodifiableCollection(allowed);
    }

    private void addTargetIfAllowed(List<MazeCell> allowed, int dx, int dy) {
        if (x + dx < 0 || x + dx >= maze.getWidth() ||
                y + dy < 0 || y + dy >= maze.getHeight()) {
            return;
        }
        MazeCell next;
        if ((next = maze.getCell(x + dx, y + dy)) != null
                && !MazeCell.Type.WALL.equals(next.getType())) {
            allowed.add(next);
        }

    }

    public MazeCell cellInFront() {
        int nextX = x, nextY = y;
        switch (currentDirection) {
            case UP:
                nextY--;
                break;
            case DOWN:
                nextY++;
                break;
            case LEFT:
                nextX--;
                break;
            case RIGHT:
                nextX++;
                break;
        }
        if (nextX < 0 || nextX >= maze.getWidth() ||
                nextY < 0 || nextY >= maze.getHeight()) {
            return null;
        }
        return maze.getCell(nextX, nextY);
    }

    public boolean moveForward() {
        MazeCell next = cellInFront();
        if (next == null || MazeCell.Type.WALL.equals(next.getType())) {
            return false;
        }
        actions.add(Action.MOVE_FORWARD);
        x = next.getX();
        y = next.getY();
        return true;
    }

    public void turnLeft() {
        actions.add(Action.TURN_LEFT);
        int directionIndex = DIRECTION_ORDER.indexOf(currentDirection);
        directionIndex = (directionIndex + DIRECTION_ORDER.size() - 1) % DIRECTION_ORDER.size();
        currentDirection = DIRECTION_ORDER.get(directionIndex);
    }

    public void turnRight() {
        actions.add(Action.TURN_RIGHT);
        int directionIndex = DIRECTION_ORDER.indexOf(currentDirection);
        directionIndex = (directionIndex + 1) % DIRECTION_ORDER.size();
        currentDirection = DIRECTION_ORDER.get(directionIndex);
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public static enum Action {
        MOVE_FORWARD,
        TURN_LEFT,
        TURN_RIGHT
    }

    public static enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
