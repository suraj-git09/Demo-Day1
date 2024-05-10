
import java.util.Scanner;

public class DiagonalElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize a 3x3 matrix
        int[][] matrix = new int[3][3];

        // Taking input from the user for the matrix elements
        System.out.println("Enter the elements of the 3x3 matrix:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter element at position (" + (i + 1) + ", " + (j + 1) + "): ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Print diagonal and anti-diagonal elements as a figure
        System.out.println("Diagonal and anti-diagonal elements as a figure:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j || i + j == 2) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print("  "); // Print spaces for non-diagonal elements
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
        System.out.println("Non-diagonal elements of the matrix are:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && i + j != 2) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }

        scanner.close();
    }
}


