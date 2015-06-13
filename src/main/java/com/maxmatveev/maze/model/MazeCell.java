package com.maxmatveev.maze.model;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeCell implements Comparable<MazeCell> {
    public static final int DISTANCE_UNREACHABLE = Integer.MAX_VALUE - 1;
    private int x, y;
    private int distance = DISTANCE_UNREACHABLE;
    private Type type;
    private boolean visited;
    private MazeCell previous;
    public MazeCell() {
    }

    public MazeCell(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public MazeCell getPrevious() {
        return previous;
    }

    public void setPrevious(MazeCell previous) {
        this.previous = previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MazeCell mazeCell = (MazeCell) o;

        if (x != mazeCell.x) return false;
        if (y != mazeCell.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(MazeCell o) {
        return Integer.compare(distance, o.distance);
    }

    public static enum Type {
        NORMAL,
        WALL,
        START,
        FINISH
    }
}
