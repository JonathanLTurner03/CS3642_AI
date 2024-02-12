package Assignments.A1.view;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BoardViewCodeBehind {

    @FXML
    private TreeView<BoardNode> spanning_tree;

    @FXML
    private Label current_board;

    @FXML
    private Label alg_speed;

    @FXML
    private Button generate_board;

    @FXML
    private Label disclaimer;

    @FXML
    private MenuButton menu_alg;

    @FXML
    private ToggleButton expanded;

    @FXML
    private MenuItem AStar;

    @FXML
    private MenuItem BFS;

    @FXML
    private MenuItem DFS;

    @FXML
    private MenuItem UCS;

    @FXML
    private Button openPerformance;

    @FXML
    private Button runPerformanceCHeck;

    @FXML
    private Button solve_button;

    @FXML
    private Label gen_board_err;

    @FXML
    private Label no_solv_alg_err;

    @FXML
    private Label iteration_count;

    private MainViewModel viewModel;

    public BoardViewCodeBehind() {
        this.viewModel = new MainViewModel();
    }

    @FXML
    private void initialize() {
        this.current_board.textProperty().bindBidirectional(viewModel.getCurrentBoardProperty());
        this.DFS.disableProperty().bindBidirectional(viewModel.DFSProperty());
        this.UCS.disableProperty().bindBidirectional(viewModel.UCSProperty());
        this.BFS.disableProperty().bindBidirectional(viewModel.BFSProperty());
        this.AStar.disableProperty().bindBidirectional(viewModel.AStarProperty());
        this.gen_board_err.visibleProperty().bindBidirectional(viewModel.genBoardErrProperty());
        this.no_solv_alg_err.visibleProperty().bindBidirectional(viewModel.solvingAlgErrProperty());
        this.expanded.selectedProperty().bindBidirectional(viewModel.expandedProperty());
        this.alg_speed.textProperty().bindBidirectional(viewModel.algSpeedProperty());
        this.disclaimer.visibleProperty().bindBidirectional(viewModel.disclaimerProperty());
        this.runPerformanceCHeck.visibleProperty().bindBidirectional(viewModel.runPerfProperty());
        this.openPerformance.visibleProperty().bindBidirectional(viewModel.openPerfProperty());
        this.iteration_count.textProperty().bindBidirectional(viewModel.iterationCounterProperty());

        this.current_board.textProperty().setValue(new Board().toString());
    }

    @FXML
    void runPerformanceCheck(ActionEvent event) {
        this.viewModel.runPerformanceCheck();
    }

    public void onGenerateBoard(ActionEvent actionEvent) {
        this.viewModel.generateBoard();
    }

    public void showSolvedPath(ActionEvent actionEvent) {
        if (this.viewModel.getSolvedRootNode() != null) {
            this.viewModel.updateDisplay();
        }
        this.spanning_tree.setRoot(viewModel.getSolvedRootNode());
    }

    public void onSolveButton(ActionEvent actionEvent) {
        viewModel.solveBoard();
        viewModel.updateDisplay();
        this.spanning_tree.setRoot(new TreeItem<>());
        this.spanning_tree.setRoot(viewModel.getSolvedRootNode());
    }

    public void onAlgChange(ActionEvent actionEvent) {

    }

    @FXML
    void openPerformance(ActionEvent event) {
        this.viewModel.openPerf();
    }

    public void onDFS(ActionEvent actionEvent) {
        viewModel.setSelectedAlg("DFS");
        this.menu_alg.textProperty().set(this.menu_alg.getItems().get(0).textProperty().get());
    }

    public void onUCS(ActionEvent actionEvent) {
        viewModel.setSelectedAlg("UCS");
        this.menu_alg.textProperty().set(this.menu_alg.getItems().get(1).textProperty().get());
    }

    public void onBFS(ActionEvent actionEvent) {
        viewModel.setSelectedAlg("BFS");
        this.menu_alg.textProperty().set(this.menu_alg.getItems().get(2).textProperty().get());
    }

    public void onAStar(ActionEvent actionEvent) {
        viewModel.setSelectedAlg("AStar");
        this.menu_alg.textProperty().set(this.menu_alg.getItems().get(3).textProperty().get());
    }
}
