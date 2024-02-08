package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.Move;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BFS {

    private final Board solved = new Board();
    private final HashSet<Board> visited = new HashSet<>();

    public Board traverse(Board root) {
        PriorityQueue<BoardNode> boards = new PriorityQueue<>();
        boards.add(new BoardNode(root));
        BoardNode node = null;
        while (!boards.isEmpty()) {
            node = boards.poll();
            if (visited.contains(node.board)) {
                continue;
            }
            visited.add(node.board);
            List<Move> children = node.board.getMoves();
            for (Move move : children) {
                Board child = new Board(node.board);
                child.swap(move);
                boards.add(new BoardNode(child));
            }
        }
        return node.board;
    }
}

class BoardNode implements Comparable<BoardNode> {

    public int heuristic;
    public Board board;

    public BoardNode(Board board) {
        this.heuristic = this.getCost(board);
        this.board = board;
    }

    private int getCost(Board board) {
        int cost = 0;
        for (int i = 0; i < 9; i++) {
            int currValue = board.getPiece(i);
            cost += Math.abs(i - this.idealIndex(i));
        }
        return cost;
    }

    private int idealIndex(int value) {
        int result = -1;
        switch(value) {
            case 0:
                result =  4;
                break;
            case 1:
                result =  0;
                break;
            case 2:
                result =  1;
                break;
            case 3:
                result =  2;
                break;
            case 4:
                result =  5;
                break;
            case 5:
                result =  8;
                break;
            case 6:
                result =  7;
                break;
            case 7:
                result =  6;
                break;
            case 8:
                result =  3;
                break;

        }
        return result;
    }

    @Override
    public int compareTo(BoardNode o) {
        return Integer.compare(this.heuristic, o.heuristic);
    }
}
