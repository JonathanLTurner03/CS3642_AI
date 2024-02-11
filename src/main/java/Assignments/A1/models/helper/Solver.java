package Assignments.A1.models.helper;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardNode;

import java.util.Comparator;

public interface Solver {

    BoardNode traverse(Board root);
}
