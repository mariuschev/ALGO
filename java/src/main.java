import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String fileName = "DNA_data.txt";
        StringBuilder result = new StringBuilder();
        System.out.println("Voici les séquences enregistrées : ");

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
        for (int i = 0; i < lines.length; i++) {
            System.out.println((i+1) + "-" + lines[i]);}
        System.out.println("~~~~~~Choisir les deux sequences a comparer~~~~~~~~");
        //écris un input contenant le numéro de la séquence qui est entre 1 et 10
        //si le numéro est plus grand que 10 ou plus petit que 1, il faut réécrire le numéro
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le numero de la premiere sequence : ");
        int num1 = scanner.nextInt();

        while (num1 > 10 || num1 < 1) {
            System.out.println("Entrer le numero de la premiere sequence : ");
            num1 = scanner.nextInt();
        }
        String Str1 = lines[num1-1];

        System.out.println("Entrer le numero de la deuxième sequence : ");
        int num2 = scanner.nextInt();
        while (num2 > 10 || num2 < 1) {
            System.out.println("Entrer le numero de la deuxième sequence : ");
            num2 = scanner.nextInt();
        }
        String Str2 = lines[num2-1];
        scanner.close();

        System.out.println(Str1 + "\n" + Str2);




        System.out.println("Entrer le numero de la deuxieme sequence : ");

        int N = Math.max(Str1.length()+1, Str2.length()+1);

        int[][] H = procedure_SW(Str1,Str2,N);

        //Affichage de la matrice en ajoutant les lettres en haut et à gauche en rouge
        System.out.print("  ");
        System.out.print("  ");
        for (int i = 0; i < Str1.length(); i++) {
            System.out.print("\033[0;31m" + Str1.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                System.out.print("\033[0;31m"+ Str2.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j < N; j++) { //print en blanc

                System.out.print("\u001B[0m" + H[i][j] + " ");
            }
            System.out.println();
        }



        //Affichage de la valeur maximale contenue dans H
        int max = 0;
        int row = 0;
        int column = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++) {
                if (H[i][j] > max) {
                    max = H[i][j];
                    row = i;
                    column = j;
                }
            }
        }
        System.out.println("\u001B[1m" + "Score max : " + max);

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