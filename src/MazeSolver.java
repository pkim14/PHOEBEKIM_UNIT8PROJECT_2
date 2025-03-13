import java.io.File;
import java.io.FileNotFoundException;

public class MazeSolver {
    private static final int[] row_moves = {0, 1, 0, -1};
    private static final int[] col_moves = {1, 0, -1, 0};

    private static String[][] maze;
    private static boolean[][] visited;
    // to store the steps in the solution
    private static int[][] solutionGrid;
    private static int stepCount = 1;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a file name.");
            return;
        }

        maze = getMaze(args[0]);
        int rows = maze.length;
        int cols = maze[0].length;
        visited = new boolean[rows][cols];
        solutionGrid = new int[rows][cols];

        // start point
        int startRow = 0;
        int startCol = 0;

        // end point
        int endRow = rows - 1;
        int endCol = cols - 1;

        if (!maze[startRow][startCol].equals(".")) {
            System.out.println("Invalid starting point.");
            return;
        }

        if (!maze[endRow][endCol].equals(".")) {
            System.out.println("Invalid end point.");
            return;
        }

        // mark starting position
        visited[startRow][startCol] = true;
        solutionGrid[startRow][startCol] = stepCount++;

        // solve the maze
        if (solveMaze(startRow, startCol, endRow, endCol)) {
            printSolutionPath(endRow, endCol);
        }
        else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solveMaze(int row, int col, int endRow, int endCol) {

        // if the end has been reached
        if (row == endRow && col == endCol) {
            return true;
        }

        // test possible directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + row_moves[i];
            int newCol = col + col_moves[i];

            // check if the new position is good
            if (isValidMove (newRow, newCol)) {
                visited[newRow][newCol] = true;
                solutionGrid[newRow][newCol] = stepCount++;

                if (solveMaze(newRow, newCol, endRow, endCol)) {
                    return true;
                }
                 // when faced with a deadend
                solutionGrid[newRow][newCol] = 0;
                stepCount--;
            }
        }
        return false;
    }
}
