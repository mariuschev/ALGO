import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        String fileName = "DNA_data.txt";
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("A") || line.startsWith("T") || line.startsWith("C") || line.startsWith("G")) {
                    result.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        //diviser la string result par le /n
        String[] lines = result.toString().split("\n");
        System.out.println(lines[0]);
    }
}

