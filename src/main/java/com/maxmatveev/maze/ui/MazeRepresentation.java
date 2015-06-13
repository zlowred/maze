package com.maxmatveev.maze.ui;

import com.maxmatveev.maze.model.MazeCell;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max Matveev on 10/06/15.
 */
public abstract class MazeRepresentation {
    public static MazeRepresentation defaultRepresentation() {
        return new MazeRepresentation() {
            private Map<MazeCell.Type, Character> typeRepresentations = new HashMap<MazeCell.Type, Character>() {{
                put(MazeCell.Type.FINISH, 'F');
                put(MazeCell.Type.START, 'S');
                put(MazeCell.Type.WALL, '#');
                put(MazeCell.Type.NORMAL, ' ');
            }};

            @Override
            public char characterForCellType(MazeCell.Type type) {
                return typeRepresentations.get(type);
            }

            @Override
            public char characterForPath() {
                return '.';
            }
        };
    }

    public abstract char characterForCellType(MazeCell.Type type);

    public abstract char characterForPath();
}
