package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.BoardNode;
import Assignments.A1.solving_algorithms.DFS;
import Assignments.A1.solving_algorithms.comparators.AStar;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Board will be used to save locations in a 2D array.
 * <p>
 * The design of the board locations will be numbered like so:
 * 0   1   2
 * 3   4   5
 * 6   7   8
 * <p>
 * 1   2   3
 * 8   0   4
 * 7   6   5
 * <p>
 * The values will be specified with an int. Must be between (inclusive) 1-8.
 * The empty spot will be represented with null.
 */
public class Driver {

    public static void main(String[] args) {
        ArrayList<Long> timer = new ArrayList<>();
        int successes = 0;
        int runs = 1;
        BoardNode node = null;
        for (int run = 0; run < runs; run++) {
            Board board = BoardGenerator.generateBoard();
            DFS solver = new DFS();
//            BFS solver = new BFS();
//            UCS solver = new UCS();
//            AStar solver = new AStar();
            Date start = new Date();
            BoardNode result = solver.traverse(board);
            Date end = new Date();
            if (result.board != null) {
                long runtime = end.getTime() - start.getTime();

                timer.add(runtime);
                successes++;
                node = result;
            }
        }
        long total = 0;
        for (int i = 0; i < timer.size(); i++) {
            total += timer.get(i);
            System.out.println("Run " + (i + 1) + ": " + timer.get(i));
        }
        long average = total / (long) timer.size();
        System.out.println("Average Runtime: " + average);
        System.out.println("Number of successful solves: " + successes + "/" + runs);

        StringBuffer result = printTotalHierarchy(node);
        writeToFile("AStar", result);
    }


//    public static void main(String[] args) {
//        int[] values = {1, 2, 3, 8, 0, 5, 4, 7, 6};
//        BoardNode node = new BoardNode(new Board(values), null);
//        System.out.println(node.heuristic);
//    }


    private static StringBuffer printTotalHierarchy(BoardNode root, int depth, boolean hasMoreChildren) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < depth - 1; i++) {
            output.append("│  ");
        }
        if (hasMoreChildren) {
            output.append("├── ").append(root.board.toString()).append(System.lineSeparator());
        } else {
            output.append("└── ").append(root.board.toString()).append(System.lineSeparator());
        }

        for (int i = 0; i < root.children.size(); i++) {
            if (i == root.children.size() - 1) {
                output.append(printTotalHierarchy(root.children.get(i), depth+1, false));
            } else {
                output.append(printTotalHierarchy(root.children.get(i), depth+1, true));
            }
        }
        return output;
    }

    public static StringBuffer printTotalHierarchy(BoardNode solved) {
        BoardNode root = null;
        while (solved.parent != null) {
            solved = solved.parent;
        }
        root = solved;

        StringBuffer output = new StringBuffer();
        output.append(root.board.toString()).append(System.lineSeparator());
        if (root.children.size() == 1) {
            output.append(printTotalHierarchy(root.children.get(0), 1, false));
        } else {
            for (int i = 0; i < root.children.size(); i++) {
                if (i == root.children.size() - 1) {
                    output.append(printTotalHierarchy(root.children.get(i), 1, false));
                } else {
                    output.append(printTotalHierarchy(root.children.get(i), 1, true));
                }
            }
        }
        return output;
    }

    private static void writeToFile(String name, StringBuffer values) {
        URL resourcePath = Driver.class.getResource("/A1/results/" + name + ".txt");
        URI resourceURI = URI.create(resourcePath.toString());
        File resource = new File(resourceURI.getPath());

        try (FileWriter out = new FileWriter(resource)) {
            out.write(values.toString());
            System.out.println("Data written to file: " + name + ".txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        openDirectoryFile(resource);
    }

    private static void openDirectoryFile(File open) {
        if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(open);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
