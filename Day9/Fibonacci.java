package Day9;


import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Number: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " "); 
        }
    }

    // Recursive method to compute Fibonacci sequence
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

// public class Fibonacci {
//     public static void main(String[] args) {
        
//         Scanner sc = new Scanner(System.in);
//            System.out.print("Enter the Number: ");
//          int n = sc.nextInt();
//         for (int i = 0; i < n; i++) {
//             System.out.print(fibonacci(i) + " ");
//         }
//     }

//     // Method to compute Fibonacci sequence using Binet's formula
//     public static int fibonacci(int n) {
//         double sqrt5 = Math.sqrt(5);
//         double phi = (1 + sqrt5) / 2;
//         double fib = Math.round(Math.pow(phi, n) / sqrt5);
//         return (int) fib;
//     }
// }
