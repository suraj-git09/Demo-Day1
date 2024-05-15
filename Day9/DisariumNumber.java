package Day9;
import java.util.Scanner;
public class DisariumNumber {

    // Function to calculate the number of digits in a number
    public static int countDigits(int number) {
        int count = 0;
        while (number != 0) {
            count++;
            number = number / 10;
        }
        return count;
    }

    // Function to check if a number is a Disarium number
    public static boolean isDisarium(int number) {
        int sum = 0;
        int count = countDigits(number);
        int temp = number;

        while (temp != 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, count);
            temp = temp / 10;
            count--;
        }

        return sum == number;
    }

 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int number = sc.nextInt(); // Example number to check

        if (isDisarium(number)) {
            System.out.println(number + " is a Disarium number.");
        } else {
            System.out.println(number + " is not a Disarium number.");
        }
    }
}
