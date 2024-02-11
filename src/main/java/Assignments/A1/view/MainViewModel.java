package Assignments.A1.view;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.BoardNode;
import Assignments.A1.models.Solver;
import Assignments.A1.solving_algorithms.AStar;
import Assignments.A1.solving_algorithms.BFS;
import Assignments.A1.solving_algorithms.UCS;
import javafx.beans.property.*;
import javafx.scene.control.TreeItem;

import java.util.Date;

public class MainViewModel {

    private StringProperty currentBoardProperty, algSpeedProperty;
    private BooleanProperty expanded, DFS, UCS, BFS, AStar, solvingAlgErr, genBoardErr;
    private String selectedAlg;

    private BoardNode current = new BoardNode(new Board(), null);
    private TreeItem<BoardNode> solvedRootNode = null;
    private BoardNode solvedRootBoardNode = null;

    public MainViewModel() {
        this.currentBoardProperty = new SimpleStringProperty();
        this.algSpeedProperty = new SimpleStringProperty();
        this.expanded = new SimpleBooleanProperty();
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
        Date start = new Date();
        BoardNode solved = solver.traverse(this.current.board);
        Date end = new Date();
        long runtime = end.getTime() - start.getTime();
        this.algSpeedProperty.setValue(String.valueOf(runtime));

        currentBoardProperty.setValue(solved.board.toString());
        BoardNode root = solved;
        while (root.parent != null) {
            root = root.parent;
        }
        this.solvedRootNode = rebuildTree(root, this.expanded.getValue());
        this.solvedRootBoardNode = root;
    }

    public void updateDisplay() {
        TreeItem<BoardNode> newRoot = null;
        newRoot = this.rebuildTree(this.solvedRootBoardNode, this.expanded.getValue());
        this.solvedRootNode = newRoot;
    }

    public TreeItem<BoardNode> getSolvedRootNode() {
        return solvedRootNode;
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

    private TreeItem<BoardNode> rebuildTree(BoardNode root, boolean expanded) {
        TreeItem<BoardNode> treeItem = new TreeItem<>(root);
        for (BoardNode child : root.children) {
            TreeItem<BoardNode> childItem = rebuildTree(child, expanded);
            treeItem.getChildren().add(childItem);
        }
        treeItem.setExpanded(expanded);

        return treeItem;
    }


    public boolean getExpanded() {
        return expanded.get();
    }

    public BooleanProperty expandedProperty() {
        return expanded;
    }

    public String getAlgSpeedProperty() {
        return algSpeedProperty.get();
    }

    public StringProperty algSpeedProperty() {
        return algSpeedProperty;
    }
}
