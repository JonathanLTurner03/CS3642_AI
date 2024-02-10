package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.BoardNode;
import Assignments.A1.solving_algorithms.BFS;
import Assignments.A1.solving_algorithms.DFS;
import Assignments.A1.solving_algorithms.UCS;

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
 *      1   2   3
 *      8   0   4
 *      7   6   5
 *
 * The values will be specified with an int. Must be between (inclusive) 1-8.
 * The empty spot will be represented with null.
 */
public class Driver {

    public static void main(String[] args) {
        ArrayList<Long> timer = new ArrayList<>();
        int successes = 0;
        int runs = 1;
        for (int run = 0; run < runs; run++) {
            Board board = BoardGenerator.generateBoard();
//            DFS solver = new DFS();
            BFS solver = new BFS();
//            UCS solver = new UCS();
            Date start = new Date();
            Board result = solver.traverse(board);
            Date end = new Date();
            if (result != null) {
                long runtime = end.getTime() - start.getTime();
                timer.add(runtime);
                successes++;
                String test = "END";
                System.out.println(solver.test.heuristic);
                BoardNode curr = solver.test;
                while (curr != null) {
                    test = curr.board.toString() + " -> " + test;
                    curr = curr.parent;
                }
                System.out.println(test);
                System.out.println();
            }
        }
        long total = 0;
        for (int i = 0; i < timer.size(); i++) {
            total += timer.get(i);
            System.out.println("Run " + (i+1) + ": " + timer.get(i));
        }
        long average = total / (long) timer.size();
        System.out.println("Average Runtime: " + average);
        System.out.println("Number of successful solves: " + successes + "/" + runs);
    }


//    public static void main(String[] args) {
//        int[] values = {1, 2, 3, 8, 0, 5, 4, 7, 6};
//        BoardNode node = new BoardNode(new Board(values), null);
//        System.out.println(node.heuristic);
//    }

}
