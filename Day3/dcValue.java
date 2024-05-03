public class dcValue {
    public static void main(String[] args) {
        double number = 123.456789; 
        float num = 123.65467f;
        String formattedNumber = String.format("%.2f", number);
        String formattedNum = String.format("%.2f", num);

        System.out.println("Formatted double number: " + formattedNumber);
        System.out.println("Formatted float number: " + formattedNum);
    }
}