package Assignments.A1.view;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.BoardNode;
import Assignments.A1.models.Solver;
import Assignments.A1.solving_algorithms.AStar;
import Assignments.A1.solving_algorithms.BFS;
import Assignments.A1.solving_algorithms.UCS;
import javafx.beans.property.*;

public class MainViewModel {

    private StringProperty currentBoardProperty, algSpeedProperty;
    private BooleanProperty showSolvedPath, DFS, UCS, BFS, AStar, solvingAlgErr, genBoardErr;
    private DoubleProperty solvingProgressProperty;
    private String selectedAlg;

    private BoardNode current = new BoardNode(new Board(), null);

    public MainViewModel() {
        this.currentBoardProperty = new SimpleStringProperty();
        this.algSpeedProperty = new SimpleStringProperty();
        this.showSolvedPath = new SimpleBooleanProperty();
        this.solvingProgressProperty = new SimpleDoubleProperty();
        this.DFS = new SimpleBooleanProperty();
        this.UCS = new SimpleBooleanProperty();
        this.BFS = new SimpleBooleanProperty();
        this.AStar = new SimpleBooleanProperty();

        this.solvingAlgErr = new SimpleBooleanProperty();
        this.genBoardErr = new SimpleBooleanProperty();
        this.solvingAlgErr.set(false);
        this.genBoardErr.set(false);
    }

    public String getSelectedAlg() {
        return this.selectedAlg;
    }

    public void setSelectedAlg(String value) {
        this.selectedAlg = value;
        this.DFS.set(value.equals("DFS"));
        this.UCS.set(value.equals("UCS"));
        this.BFS.set(value.equals("BFS"));
        this.AStar.set(value.equals("AStar"));
    }

    public StringProperty getCurrentBoardProperty() {
        return this.currentBoardProperty;
    }

    public void generateBoard() {
        this.current = new BoardNode(BoardGenerator.generateBoard(), null);
        currentBoardProperty.set(this.current.board.toString());
    }

    public void solveBoard() {
        if (selectedAlg == null || selectedAlg.isEmpty()) {
            this.solvingAlgErr.setValue(true);
            return;
        }
        this.solvingAlgErr.setValue(false);
        if (current.board.equals(new Board())) {
            this.genBoardErr.setValue(true);
            return;
        }
        this.genBoardErr.setValue(false);

        Solver solver = null;
        if (DFS.getValue()) {
//            solver = new DFS();
        } else if (UCS.getValue()) {
            solver = new UCS();
        } else if (BFS.getValue()) {
            solver = new BFS();
        } else if (AStar.getValue()) {
            solver = new AStar();
        }

        BoardNode solved = solver.traverse(this.current.board);

        System.out.println(solved);
    }


    public BooleanProperty DFSProperty() {
        return DFS;
    }

    public BooleanProperty UCSProperty() {
        return UCS;
    }

    public BooleanProperty BFSProperty() {
        return BFS;
    }

    public BooleanProperty AStarProperty() {
        return AStar;
    }

    public BooleanProperty solvingAlgErrProperty() {
        return solvingAlgErr;
    }

    public BooleanProperty genBoardErrProperty() {
        return genBoardErr;
    }


}
