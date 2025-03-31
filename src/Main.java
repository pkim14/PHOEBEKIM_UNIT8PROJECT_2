import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/txt";
        String[][] maze = MazeReader.getMaze(fileName);

        MazeSolver solver = new MazeSolver(maze);
        List<int[]> solution = solver.solve();

        MazePrinter.printSolution(solution);
    }
}