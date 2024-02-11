package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.BoardNode;
import Assignments.A1.solving_algorithms.BFS;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

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
//            DFS solver = new DFS();
            BFS solver = new BFS();
//            UCS solver = new UCS();
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

        String result = printTotalHierarchy(node);
        System.out.println(result);

    }


//    public static void main(String[] args) {
//        int[] values = {1, 2, 3, 8, 0, 5, 4, 7, 6};
//        BoardNode node = new BoardNode(new Board(values), null);
//        System.out.println(node.heuristic);
//    }


    private static String printTotalHierarchy(BoardNode root, int depth, boolean hasMoreChildren) {
        String result = "";
        for (int i = 0; i < depth - 1; i++) {
            result += "│  ";
        }
        if (hasMoreChildren) {
            result += "├── " + root.board.toString() + System.lineSeparator();
        } else {
            result += "└── " + root.board.toString() + System.lineSeparator();
        }

        for (int i = 0; i < root.children.size(); i++) {
            if (i == root.children.size() - 1) {
                result += printTotalHierarchy(root.children.get(i), depth+1, false);
            } else {
                result += printTotalHierarchy(root.children.get(i), depth+1, true);
            }
        }
        return result;
    }

    public static String printTotalHierarchy(BoardNode solved) {
        BoardNode root = null;
        while (solved.parent != null) {
            solved = solved.parent;
        }
        root = solved;

        String result = root.board.toString() + System.lineSeparator();
        if (root.children.size() == 1) {
            result += printTotalHierarchy(root.children.get(0), 1, false);
        } else {
            for (int i = 0; i < root.children.size(); i++) {
                if (i == root.children.size() - 1) {
                    result += printTotalHierarchy(root.children.get(i), 1, false);
                } else {
                    result += printTotalHierarchy(root.children.get(i), 1, true);
                }
            }
        }
        return result;
    }
}
