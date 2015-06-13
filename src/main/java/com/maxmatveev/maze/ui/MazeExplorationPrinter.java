package com.maxmatveev.maze.ui;

import com.maxmatveev.maze.model.MazeExplorer;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeExplorationPrinter {
    public String getExplorationDescription(MazeExplorer explorer) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Start at coordinates (%d, %d) facing %s\n",
                explorer.getMaze().getStart().getX(),
                explorer.getMaze().getStart().getY(),
                explorer.getInitialDirection().name().toLowerCase()));
        int steps = 0;
        for (MazeExplorer.Action action : explorer.getActions()) {
            if (action.equals(MazeExplorer.Action.MOVE_FORWARD)) {
                steps++;
                continue;
            }
            sb.append(String.format("Move %d steps forward\n", steps));
            steps = 0;
            sb.append(action.name().substring(0, 1));
            sb.append(action.name().substring(1).toLowerCase().replace('_', ' '));
            sb.append('\n');

        }
        sb.append(String.format("Move %d steps forward\n", steps));
        return sb.toString();
    }
}
