package Assignments.A1.models;

public class BoardNode implements Comparable<BoardNode> {

    public BoardNode parent;
    public int heuristic;
    public int cost;
    public Board board;
    public List<String>  

    public BoardNode(Board board, BoardNode parent) {
        this.board = board;
        this.parent = parent;
        this.heuristic = this.getHeuristic();
        this.cost = this.getActualCost();
    }

    private int getActualCost() {
        if (this.parent != null) {
            return this.parent.cost + 1;
        } else {
            return 0;
        }
    }


    private int getHeuristic() {
        int cost = 0;
        for (int i = 0; i < 9; i++) {
            int currValue = board.getPiece(i);
            int idealIndex = idealIndex(currValue);
            int currRow = i / 3;
            int currCol = i % 3;
            int idealRow = idealIndex / 3;
            int idealCol = idealIndex % 3;
            cost += Math.abs(idealRow - currRow) + Math.abs(idealCol - currCol);
        }
        return cost;
    }

    private int idealIndex(int value) {
        int result = -1;
        switch (value) {
            case 0:
                result = 4;
                break;
            case 1:
                result = 0;
                break;
            case 2:
                result = 1;
                break;
            case 3:
                result = 2;
                break;
            case 4:
                result = 5;
                break;
            case 5:
                result = 8;
                break;
            case 6:
                result = 7;
                break;
            case 7:
                result = 6;
                break;
            case 8:
                result = 3;
                break;
        }
        return result;
    }

    @Override
    public int compareTo(BoardNode o) { // BFS
        return Integer.compare(this.heuristic, o.heuristic);
    }
}
