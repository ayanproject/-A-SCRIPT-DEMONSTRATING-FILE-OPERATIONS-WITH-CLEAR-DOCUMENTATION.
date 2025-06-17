import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOperationsDemo {
    // File path - change this path as needed
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        // 1. Write content to file
        writeToFile("Hello, this is a demo file.\nIt will be read and modified.\n");

        // 2. Read content from file
        System.out.println("=== Reading File ===");
        readFromFile();

        // 3. Modify file (replace "demo" with "sample")
        modifyFile("demo", "sample");

        // 4. Read modified content
        System.out.println("\n=== Reading Modified File ===");
        readFromFile();
    }

    /**
     * Writes given content to a file (overwrites if exists)
     */
    public static void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and prints the content of the file
     */
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Replaces occurrences of a target string with a replacement in the file
     */
    public static void modifyFile(String target, String replacement) {
        try {
            // Read all lines into a list
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            List<String> modifiedLines = new ArrayList<>();

            for (String line : lines) {
                modifiedLines.add(line.replace(target, replacement));
            }

            // Write modified lines back to the file
            Files.write(Paths.get(FILE_PATH), modifiedLines);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }
}
