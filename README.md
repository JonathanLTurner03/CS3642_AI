### CS3642 Artificial Intelligence
This repository is to track the development process for my classwork.
Some projects implement JavaFX via Maven package management in IntelliJ. These projects can be ran using the .iml files which are configured to run each project.
These projects will also be available in a self-packaged .jar file in the releases when time permits. 

## Assignment 1 - 8 Puzzle Solver
This application was focused around pathing and path generation and traversal. The goal of the assignment was to work with the different graph traversal algorithms to understand their strengths, weaknesses, and use-cases.
# Traversal Algorithms
These algorithms were [A* (A Star)](src/A1_8Puzzles/solving_algorithms/comparators/AStar.java), [BFS (Best-First Search)](src/A1_8Puzzles/solving_algorithms/comparators/BFS.java), [UCS (Uniform-Cost Search)](src/A1_8Puzzles/solving_algorithms/comparators/UCS.java), and lastly [DFS (Depth-First Search)](src/A1_8Puzzles/solving_algorithms/DFS.java). 
The first 3 algorithms were implemented using a Priority Queue and a comparator that will change how the queue will prioritize the next moves. These comparators were then passed into [PriorityTraversal](src/A1_8Puzzles/solving_algorithms/PriorityTraversal.java).
The last algorithm DFS was implemented using a Stack for LIFO (Last-In -> First-Out) Priority. 
# JavaFX Implementation 
The GUI is rudimentary and is mainly focused on displaying the current board, *does not display the traversal algorithm*, and will show a directory representation of the tree generated from the graph traversal. 
The GUI will also allow you to select which algorithm you would like to solve for and run performance analysis of that algorithm with 100 sequential runs. From these runs it will check the time taken, outliers and average, and will display the average number of nodes. 
These results are saved during runtime so after running once, you can go back to that algorithm and view the previous test without needing to run again. 
