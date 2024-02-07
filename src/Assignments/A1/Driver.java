package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.solving_algorithms.DFS;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Board will be used to save locations in a 2D array.
 *
 * The design of the board locations will be numbered like so:
 *      0   1   2
 *      3   4   5
 *      6   7   8
 *
 * The values will be specified with an int. Must be between (inclusive) 1-8.
 * The empty spot will be represented with null.
 */
public class Driver {

    public static void main(String[] args) {
        ArrayList<Long> timer = new ArrayList<>();
        int successes = 0;
        for (int run = 0; run < 100; run++) {
            Board board = BoardGenerator.generateBoard();
            DFS solver = new DFS();
            Date start = new Date();
            Board result = solver.dfs(board,0);
            Date end = new Date();
            if (result != null) {
                System.out.println("solved");
                long runtime = end.getTime() - start.getTime();
                timer.add(runtime);
                successes++;
            }
        }
        long total = 0;
        for (int i = 0; i < timer.size(); i++) {
            total += timer.get(i);
            System.out.println("Run " + (i+1) + ": " + timer.get(i));
        }
        long average = total / (long) timer.size();
        System.out.println("Average Runtime: " + average);
        System.out.println("Number of successful solves: " + successes + "/100");
    }

}
