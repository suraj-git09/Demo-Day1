

import java.util.Scanner;

public class ElectricityBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter units consumed: ");
        int unitsConsumed = scanner.nextInt();
        
        int billAmount = (unitsConsumed <= 50) ? unitsConsumed * 2 :
                        (unitsConsumed <= 150) ? 100 + (unitsConsumed - 100) * 3 :
                        (unitsConsumed <= 200) ? 250 + (unitsConsumed - 150) * 4 :
                        (unitsConsumed <= 250) ? 450 + (unitsConsumed - 200) * 5 :
                        (unitsConsumed <= 300) ? 700 + (unitsConsumed - 250) * 6 :
                        (unitsConsumed <= 500) ? 1150 + (unitsConsumed - 300) * 7 :
                        2600 + (unitsConsumed - 500) * 8;
        
        System.out.println("Electricity Bill: Rupees " + billAmount);
    }
}
