// first
import java.util.Scanner;
import java.nio.file.Paths;
public class main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try (Scanner scanner = new Scanner(Paths.get("DNA_data.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                System.out.println(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }











    }
}
