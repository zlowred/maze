package com.maxmatveev.maze;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class MazeLoadException extends Exception {
    public MazeLoadException() {
        super();
    }

    public MazeLoadException(String message) {
        super(message);
    }

    public MazeLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MazeLoadException(Throwable cause) {
        super(cause);
    }
}
