import java.util.Scanner;

public class Isprime {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number to check: ");
        int n = sc.nextInt();
        int i;

        for( i=2;i<n;i++)
    {
        if(n%i==0)
        {
            System.out.println("Its a Non-Prime No.");
            break;
        }
    }

    if( i==n)
    {
        System.out.println("Its a Prime No.");

    }
}
    }

