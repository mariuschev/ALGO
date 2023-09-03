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


        String Str1 = "GTCGATTTGA";
        String Str2 = "ACGAAAGAGG";
        int N = Math.max(Str1.length()+1, Str2.length()+1);

        int[][] H = procedure_SW(Str1,Str2,N);

        //Affichage de la matrice

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(H[i][j] + " ");
            }
            System.out.println();
        }


    }
    public static int[][] procedure_SW(String Str1, String Str2, int N){
        int[][] H = new int[N][N];
        //initialiser première ligne
        for (int Row = 0; Row < N; Row++) {
            //initialiser première colonne
            for (int Column = 0; Column < N; Column++) {
                if (Row == 0 || Column == 0) {
                    // Si nous sommes à la première ligne ou à la première colonne, initialiser à 0
                    H[Row][Column] = 0;
                } else {
                    int score;

                    if (Str1.charAt(Row - 1) == Str2.charAt(Column - 1)) {
                        score = 1;

                    } else {
                        score = 0;
                    }


                    // Calculer la distance en utilisant Equation (1)
                    H[Row][Column] = calculateDistance(H, Row, Column, score);

                }


            }
        }
        return H;
    }

    public static int calculateDistance(int[][] H, int Row, int Column, int score) {
        int Gap = -2;

        int option1 = Math.max(H[Row - 1][Column - 1] + score, 0);

        int option2 = Math.max(H[Row - 1][Column] + Gap, Gap);
        int option3 = Math.max(H[Row][Column - 1] + Gap, Gap);

        return Math.max(Math.max(option1, option2), option3);
    }

}