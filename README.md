# CS3642 Artificial Intelligence
This repository is to track the development process for my classwork.
Some projects implement JavaFX via Maven package management in IntelliJ. These projects can be ran using the .iml files which are configured to run each project.
These projects will also be available in a self-packaged .jar file in the releases when time permits. 

### JavaDoc Commenting Structure
All of my finished code and any public class should contain a JavaDoc comment block. This comment block will contain the what parameters the function accepts, what are the preconditions, what are the post conditions and what values it returns *(if not void)*.
This commenting structure along with designing with Self-Documenting code it should allow for easier understanding and code tracing when troubleshooting, revising, or utilizing the functions. 
## Assignment 1 - 8 Puzzle Solver
This application was focused around pathing with regards to path generation and graph traversal. The goal of the assignment was to work with the different graph traversal algorithms to understand their strengths, weaknesses, and use-cases.
### Initial Design Model
This application was modeled closely with MVVM (Model-View-ViewModel) Structure. This allows for a seperation of concerns and to ensure no user interacts directly with a model, no model, interacts directly with the user, and that there is a intermediatary, [MainViewModel](src/main/java/Assignments/A1_8Puzzles/view/MainViewModel.java), that will bind with the View, [BoardView](src/main/resources/A1/view/BoardView.fxml), via the Property file, [BoardViewCodeBehind](src/main/java/Assignments/A1_8Puzzles/view/BoardViewCodeBehind.java).

From this design method I began the module classes. The structure for these are as follows: The main 3 are [Board](src/main/java/Assignments/A1_8Puzzles/models/Board.java), [BoardNode](src/main/java/Assignments/A1_8Puzzles/models/BoardNode.java) and [BoardGenerator](src/main/java/Assignments/A1_8Puzzles/models/BoardGenerator.java). The board focused on holding the positions and functionality a board could perform at a given moment. This included the available [Moves](src/main/java/A1_8Puzzles/Assignments/models/helper/Move.java) and attributes associated with each algorithm. Each Board calculates a heuristic *(estimated future cost)*, an actual cost *(cost to get to this node)*, and a total cost *(actual cost + estimated cost)*. These attributes were then utilized by the different traversal algorithms to change the priority of each node. 
### Traversal Algorithms
These algorithms were [A* (A Star)](src/main/java/Assignments/A1_8Puzzles/solving_algorithms/comparators/AStar.java), [BFS (Best-First Search)](src/main/java/Assignments/A1_8Puzzles/solving_algorithms/comparators/BFS.java), [UCS (Uniform-Cost Search)](src/main/java/Assignments/A1_8Puzzles/solving_algorithms/comparators/UCS.java), and lastly [DFS (Depth-First Search)](src/main/java/Assignments/A1_8Puzzles/solving_algorithms/DFS.java). 
The first 3 algorithms were implemented using a Priority Queue and a comparator that will change how the queue will prioritize the next moves. These comparators were then passed into [PriorityTraversal](src/main/java/Assignments/A1_8Puzzles/solving_algorithms/PriorityTraversal.java).
The last algorithm DFS was implemented using a Stack for LIFO (Last-In -> First-Out) Priority. 
### JavaFX Implementation 
The GUI is rudimentary and is mainly focused on displaying the current board, *does not display the traversal algorithm*, and will show a directory representation of the tree generated from the graph traversal. 
The GUI will also allow you to select which algorithm you would like to solve for and run performance analysis of that algorithm with 100 sequential runs. From these runs it will check the time taken, outliers and average, and will display the average number of nodes. 
These results are saved during runtime so after running once, you can go back to that algorithm and view the previous test without needing to run again. 
