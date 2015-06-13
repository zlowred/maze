package com.maxmatveev.maze;

import com.maxmatveev.maze.model.Maze;
import com.maxmatveev.maze.model.MazeExplorer;
import com.maxmatveev.maze.model.MazeSolution;
import com.maxmatveev.maze.service.MazeExplorationResolver;
import com.maxmatveev.maze.service.MazeLoader;
import com.maxmatveev.maze.service.MazeSolver;
import com.maxmatveev.maze.ui.MazeExplorationPrinter;
import com.maxmatveev.maze.ui.MazeRepresentation;
import com.maxmatveev.maze.ui.MazeSolutionPrinter;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Max Matveev on 10/06/15.
 */
public class Main {
    public static void main(String[] args) {
        try {
            File mazeFile;

            if (args.length > 0) {
                mazeFile = new File(args[0]);
                System.out.println("Using maze file: [" + args[0] + "]\n");
            } else {
                System.out.println("Maze file name not provided; using default one\n");
                URI mazeUri = Main.class.getClassLoader().getResource("ExampleMaze.txt").toURI();
                mazeFile = new File(mazeUri);
            }
            MazeLoader loader = new MazeLoader();
            Maze maze = loader.loadMaze(mazeFile);
            MazeSolver solver = new MazeSolver();
            MazeSolution solution = solver.solveMaze(maze);
            MazeSolutionPrinter solutionPrinter = new MazeSolutionPrinter(MazeRepresentation.defaultRepresentation());
            System.out.println(solutionPrinter.getSolutionRepresentation(solution));
            MazeExplorationResolver resolver = new MazeExplorationResolver();
            MazeExplorer explorer = resolver.resolveSolution(solution);
            System.out.println("Step-by-step solution:");
            MazeExplorationPrinter explorationPrinter = new MazeExplorationPrinter();
            System.out.println(explorationPrinter.getExplorationDescription(explorer));
        } catch (URISyntaxException | MazeLoadException e) {
            e.printStackTrace();
        }
    }
}
