 package Day10;

// public class IncrementStringNumber {

//     public static void main(String[] args) {
//         // Initial string representing the number
//         String numberStr = "2000";
        
//         // Convert the string to an integer
//         int number = Integer.parseInt(numberStr);
        
//         // Increment the number by 1
//         number = number + 1;
        
//         // Convert the integer back to a string
//         String incrementedNumberStr = Integer.toString(number);
        
//         // Print the result
//         System.out.println("Original number: $" + numberStr);
//         System.out.println("Incremented number: $" + incrementedNumberStr);
//     }
// }
public class IncrementStringNumber {

    public static void main(String[] args) {
        // Initial string representing the amount with a dollar sign
        String amountStr = "$2000";
        
        // Extract the numeric part of the string
        String numericPart = amountStr.substring(1); // Remove the dollar sign
        
        // Convert the numeric part to an integer
        int number = Integer.parseInt(numericPart);
        
        // Increment the number by 1
        number = number + 1;
        
        // Convert the incremented number back to a string
        String incrementedNumericPart = Integer.toString(number);
        
        // Concatenate the dollar sign with the incremented numeric string
        String incrementedAmountStr = "$" + incrementedNumericPart;
        
        // Print the result
        System.out.println("Original amount: " + amountStr);
        System.out.println("Incremented amount: " + incrementedAmountStr);
    }
}
