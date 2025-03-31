import java.util.*;

public class MazeSolver {
    private String[][] maze;
    private boolean[][] visited;
    private List<int[]> path;
    private int[] row_moves = {0, 1, 0, -1};
    private int[] col_moves = {1, 0, -1, 0};

    public MazeSolver(String[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
        this.path = new ArrayList<>();
    }

    public List<int[]> solve() {
        if (findPath(0, 0)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean findPath(int row, int col) {
        if (!isValid(row, col)) {
            return false;
        }

        visited[row][col] = true;
        path.add(new int[]{row, col});

        if (row == maze.length - 1 && col == maze[0].length - 1) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row + row_moves[i];
            int newCol = col + col_moves[i];

            if (findPath(newRow, newCol)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col].equals(".") && !visited[row][col];
    }
}





