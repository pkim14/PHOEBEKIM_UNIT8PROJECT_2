import java.util.List;

public class MazePrinter {
    public static void printSolution(List<int[]> path) {
        if (path.isEmpty()) {
            System.out.println("No Solution found.");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            int[] point = path.get(i);
            System.out.println("(" + point[0] + ", " + point[1] + ")");
            if (i < path.size() - 1) {
                System.out.println(" ---> ");
            }
        }
        System.out.println();
    }
}
