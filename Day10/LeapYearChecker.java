package Day10;

import java.util.Scanner;

public class LeapYearChecker {

    // Function to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // Year is divisible by 100
                if (year % 400 == 0) {
                    // Year is divisible by 400
                    return true;
                } else {
                    // Year is not divisible by 400
                    return false;
                }
            } else {
                // Year is not divisible by 100
                return true;
            }
        } else {
            // Year is not divisible by 4
            return false;
        }
    }

    // Main method to test the function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        
        if (isLeapYear(year)) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }

        scanner.close();
    }
}
