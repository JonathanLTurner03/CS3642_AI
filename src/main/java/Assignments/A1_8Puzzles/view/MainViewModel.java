package Assignments.A1_8Puzzles.view;

import Assignments.A1_8Puzzles.models.Board;
import Assignments.A1_8Puzzles.models.BoardGenerator;
import Assignments.A1_8Puzzles.models.BoardNode;
import Assignments.A1_8Puzzles.models.helper.Solver;
import Assignments.A1_8Puzzles.solving_algorithms.PriorityTraversal;
import Assignments.A1_8Puzzles.solving_algorithms.comparators.AStar;
import Assignments.A1_8Puzzles.solving_algorithms.comparators.BFS;
import Assignments.A1_8Puzzles.solving_algorithms.DFS;
import Assignments.A1_8Puzzles.solving_algorithms.comparators.UCS;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;

import java.util.Date;
import java.util.Stack;

public class MainViewModel {

    /* Private Fields used to bind */
    private final StringProperty currentBoardProperty, algSpeedProperty, iterationCounter;
    private final BooleanProperty expanded, DFS, UCS, BFS, AStar, solvingAlgErr, genBoardErr, disclaimer, openPerf, runPerf;
    private BoardNode current = new BoardNode(new Board(), null);
    private TreeItem<BoardNode> solvedRootNode = null;

    /* Private Fields used for traversing/creating nodes. */
    private BoardNode solvedRootBoardNode = null;
    private final long[][] hasRanPerformance;
    private String selectedAlg;

    /**
     * Initializes all the Properties and initial values for any fields.
     *
     * @precondition none
     * @postcondition a VM is created.
     */
    public MainViewModel() {
        this.currentBoardProperty = new SimpleStringProperty();
        this.algSpeedProperty = new SimpleStringProperty();
        this.disclaimer = new SimpleBooleanProperty();
        this.expanded = new SimpleBooleanProperty();
        this.openPerf = new SimpleBooleanProperty();
        this.runPerf = new SimpleBooleanProperty();
        this.DFS = new SimpleBooleanProperty();
        this.UCS = new SimpleBooleanProperty();
        this.BFS = new SimpleBooleanProperty();
        this.AStar = new SimpleBooleanProperty();
        this.iterationCounter = new SimpleStringProperty();

        this.solvingAlgErr = new SimpleBooleanProperty();
        this.genBoardErr = new SimpleBooleanProperty();
        this.solvingAlgErr.set(false);
        this.genBoardErr.set(false);

        this.hasRanPerformance = new long[4][102];
    }

    /**
     * Generates the board and sets the board on view to new board.
     *
     * @precondition none
     * @postcondition none
     */
    public void generateBoard() {
        this.current = new BoardNode(BoardGenerator.generateBoard(), null);
        currentBoardProperty.set(this.current.board.toString());
    }

    /**
     * Solves the current board by checking for which alg is selected, then will solve that board
     * with the given alg that implements the Solver interface.
     *
     * @precondition menuItem != null && (board != solver || board != null)
     * @postcondition the board is solved.
     */
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
            solver = new DFS();
        } else if (UCS.getValue()) {
            solver = new PriorityTraversal(new UCS());
        } else if (BFS.getValue()) {
            solver = new PriorityTraversal(new BFS());
        } else if (AStar.getValue()) {
            solver = new PriorityTraversal(new AStar());
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
        this.current = solved;
    }

    /**
     * Sets the values of the data using the View's Property files and Bindings.
     *
     * @precondition none
     * @postcondition none
     *
     * @param value the menu item value.
     */
    public void setSelectedAlg(String value) {
        this.selectedAlg = value;
        this.DFS.set(value.equals("DFS"));
        this.UCS.set(value.equals("UCS"));
        this.BFS.set(value.equals("BFS"));
        this.AStar.set(value.equals("AStar"));

        this.disclaimer.setValue(value.equals("DFS"));
        this.openPerf.setValue(this.hasRanPerformance[convertValueToInt(value)][0] == 1);
        this.runPerf.setValue(this.hasRanPerformance[convertValueToInt(value)][0] == 0);
        if (this.hasRanPerformance[convertValueToInt(value)][0] == 0) {
            this.iterationCounter.set("");
        }
    }

    /**
     * Runs through the selected algorithm and will run it 100 times. It will collect data from these runs
     * and store them. It will then allow you to view the data in a new windows and the button will appear.
     *
     * @precondition none
     * @postcondition the performance is taken.
     */
    public void runPerformanceCheck() {
        Solver solver = null;
        int alg = -1;
        if (this.selectedAlg.equals("DFS") && this.hasRanPerformance[0][0] == 0) {
            solver = new DFS();
            alg = 0;
        } else if (this.selectedAlg.equals("UCS") && this.hasRanPerformance[1][0] == 0) {
            solver = new PriorityTraversal(new UCS());
            alg = 1;
        } else if (this.selectedAlg.equals("BFS") && this.hasRanPerformance[2][0] == 0) {
            solver = new PriorityTraversal(new BFS());
            alg = 2;
        } else if (this.selectedAlg.equals("AStar") && this.hasRanPerformance[3][0] == 0) {
            solver = new PriorityTraversal(new AStar());
            alg = 3;
        }
        int averageNodes = 0;
        if (solver != null) {
            for (int i = 0; i < 100; i++) {
                Board board = BoardGenerator.generateBoard();
                System.out.println(board);
                long start = new Date().getTime();
                BoardNode node = solver.traverse(board);
                long end = new Date().getTime();
                System.out.println(node.board + "\n");
                if (node != null) {
                    this.hasRanPerformance[alg][i + 2] = end - start;
                    this.currentBoardProperty.setValue(node.board.toString());
                    averageNodes += solver.getNumOfNodes();
                    updateDisplay();
                } else {
                    this.hasRanPerformance[alg][i+2] = -1;
                }
                iterationCounter.setValue("Performing Analysis Iteration: " + (i+1) + "/100");
            }
            this.hasRanPerformance[alg][0] = 1;
            this.hasRanPerformance[alg][1] = averageNodes;
            this.runPerf.setValue(false);
            this.openPerf.setValue(true);
        }

        for (int i = 1; i < this.hasRanPerformance[alg].length; i++) {
            System.out.println(this.hasRanPerformance[alg][i]);
        }
    }

    /**
     * Opens the performance files that were previously generated. Is only visible when performance is already generated.
     *
     * @precondition performance == generated
     * @postcondition performance is displayed in Alert.
     */
    public void openPerf() {
        long totalTime = 0;
        int success = 0;
        long shortest = 1000000;
        long longest = -1;
        for (int i = 2; i < this.hasRanPerformance[convertValueToInt(this.selectedAlg)].length; i++) {
            long current = this.hasRanPerformance[convertValueToInt(this.selectedAlg)][i];
            if (current != -1) {
                success++;
                totalTime += current;
                if (current < shortest) {
                    shortest = current;
                }
                if (longest < current) {
                    longest = current;
                }
            }
        }
        long average = totalTime / (long) success;

        Alert perf = new Alert(Alert.AlertType.INFORMATION);

        perf.setTitle(this.selectedAlg + " Performance Report");
        perf.setHeaderText(success + "/" + 100 + " Succeeded.");
        String results = "Shortest Run: " + shortest + "ms\n" +
                "Longest Run: " + longest + "ms\n" +
                "Average Time: " + average + "ms" + "\n" +
                "Average Nodes: " + (this.hasRanPerformance[convertValueToInt(this.selectedAlg)][1]/success);
        perf.setContentText(results);
        perf.show();
    }

    /**
     * Is used to update the display with the current board.
     *
     * @precondition none
     * @postcondition the dispaly is updated.
     */
    public void updateDisplay() {
        TreeItem<BoardNode> newRoot = null;
        newRoot = this.rebuildTree(this.solvedRootBoardNode, this.expanded.getValue());
        this.solvedRootNode = newRoot;
    }

    /* Getters for bindings */

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

    public boolean getExpanded() {
        return expanded.get();
    }

    public BooleanProperty expandedProperty() {
        return expanded;
    }

    public StringProperty algSpeedProperty() {
        return algSpeedProperty;
    }

    public StringProperty iterationCounterProperty() {
        return iterationCounter;
    }

    public boolean isDisclaimer() {
        return disclaimer.get();
    }

    public BooleanProperty disclaimerProperty() {
        return disclaimer;
    }

    public BooleanProperty openPerfProperty() {
        return openPerf;
    }

    public BooleanProperty runPerfProperty() {
        return runPerf;
    }

    public StringProperty getCurrentBoardProperty() {
        return this.currentBoardProperty;
    }

    /* End of Public Methods. Private Fields Only */

    private TreeItem<BoardNode> rebuildTree(BoardNode root, boolean expanded) {
        if (this.selectedAlg != null) {
            if (this.selectedAlg.equals("DFS")) {
                Stack<BoardNode> generations = new Stack<>();
                BoardNode temp = this.current;
                while (temp != null) {
                    generations.push(temp);
                    temp = temp.parent;
                }
                TreeItem<BoardNode> rootItem = new TreeItem<>(root);
                TreeItem<BoardNode> currItem = rootItem;
                while (!generations.isEmpty()) {
                    TreeItem<BoardNode> child = new TreeItem<>(generations.pop());
                    currItem.getChildren().add(child);
                    currItem.setExpanded(expanded);
                    currItem = child;
                }
                return rootItem;
            } else {
                TreeItem<BoardNode> treeItem = new TreeItem<>(root);
                for (BoardNode child : root.children) {
                    TreeItem<BoardNode> childItem = rebuildTree(child, expanded);
                    treeItem.getChildren().add(childItem);
                }
                treeItem.setExpanded(expanded);

                return treeItem;
            }
        }
        return new TreeItem<>();
    }

    private int convertValueToInt(String value) {
        if (value.equals("DFS")) return 0;
        if (value.equals("UCS")) return 1;
        if (value.equals("BFS")) return 2;
        return 3;
    }
}
