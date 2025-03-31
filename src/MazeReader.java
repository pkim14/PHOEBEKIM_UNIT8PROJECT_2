import java.io.*;
import java.util.*;

public class MazeReader {
    public static String[][] getMaze(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> lines = new ArrayList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Error: Maze file is empty or invalid.");
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        String[][] maze = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            if(line.length() != cols) {
                throw new IllegalArgumentException("Error: Inconsistent row lengths in maze file.");
            }

            for (int j = 0; j < cols; j++) {
                maze[i][j] = String.valueOf(line.charAt(j));
            }
        }
        return maze;
    }

}
