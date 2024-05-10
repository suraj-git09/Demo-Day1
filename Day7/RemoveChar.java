public class RemoveChar {
    public static void main(String[] args) {
        String str = "Hello*World*!";
        String removedAsterisks = str.replace("*", "");
        System.out.println("Original String: " + str);
        System.out.println("String after removing asterisks: " + removedAsterisks);
    }
}