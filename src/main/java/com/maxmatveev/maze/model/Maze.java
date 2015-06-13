package com.maxmatveev.maze.model;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class Maze {
    private MazeCell[][] data;
    private MazeCell start;
    private MazeCell finish;

    public Maze(int width, int height) {
        data = new MazeCell[height][];
        for (int i = 0; i < height; i++) {
            data[i] = new MazeCell[width];
        }
    }

    public void setCell(MazeCell cell) {
        int x = cell.getX(), y = cell.getY();
        if (start != null && start.getX() == x && start.getY() == y) {
            start = null;
        }
        if (finish != null && finish.getX() == x && finish.getY() == y) {
            finish = null;
        }
        data[y][x] = cell;
        if (MazeCell.Type.FINISH.equals(cell.getType())) {
            if (finish != null) {
                throw new IllegalStateException("Only one finish cell is allowed; cannot add second finish cell");
            }
            finish = cell;
        }
        if (MazeCell.Type.START.equals(cell.getType())) {
            if (start != null) {
                throw new IllegalStateException("Only one start cell is allowed; cannot add second start cell");
            }
            start = cell;
            cell.setDistance(0);
        }
    }

    public MazeCell getStart() {
        return start;
    }

    public MazeCell getFinish() {
        return finish;
    }

    public int getWidth() {
        return data[0].length;
    }

    public int getHeight() {
        return data.length;
    }

    public MazeCell[][] getData() {
        return data;
    }

    public MazeCell getCell(int x, int y) {
        return data[y][x];
    }

    public boolean isValid() {
        return start != null && finish != null && !start.equals(finish);
    }
}
