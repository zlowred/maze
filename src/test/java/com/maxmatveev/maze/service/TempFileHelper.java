package com.maxmatveev.maze.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class TempFileHelper {
    public static File createTempFile(String contents) throws IOException {
        File tempFile = File.createTempFile("maze", "test");
        new PrintWriter(tempFile).append(contents).close();
        return tempFile;
    }
}
