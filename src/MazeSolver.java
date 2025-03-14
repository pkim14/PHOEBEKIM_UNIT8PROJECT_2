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

        maze = MazeRunner.getMaze();
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
                 // when faced with a dead end
                solutionGrid[newRow][newCol] = 0;
                stepCount--;
            }
        }
        return false;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }

        // checks if it is a wall
        if (maze[row][col].equals("#")) {
            return false;
        }

        if (visited[row][col]) {
            return false;
        }
        return true;
    }

    // solution path?
    private static void printSolutionPath(int endRow, int endCol) {
        int totalSteps = solutionGrid[endRow][endCol];

        // find each step in the solution grid
        int[][] pathCoordinates = new int[totalSteps][2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (solutionGrid[i][j] > 0) {
                    pathCoordinates[solutionGrid[i][j] - 1] = new int[]{i, j};
                }
            }
        }

        StringBuilder sb = new StringBuilder(); // might change string builder to smth more simple?
        for (int i =0; i < pathCoordinates.length; i++) {
            int[] position = pathCoordinates[i];
            sb.append("(").append(position[0]).append(", ").append(position[1]).append(")");

            if (i < pathCoordinates.length - 1) {
                sb.append(" ---> ");
            }
        }
        System.out.println(sb.toString());
    }
}
