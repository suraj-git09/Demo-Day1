import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String S = sc.nextLine();
        for( int i= S.length()-1; i>=0; i--){
            System.out.print(S.charAt(i));
        }
    }

    
}    