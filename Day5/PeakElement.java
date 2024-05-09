

import java.util.Scanner;



public class PeakElement {
public static void main(String[] args) {
    
Scanner sc= new Scanner(System.in);
System.out.print("Enter the no. of elements ");
int n=sc.nextInt();

        int a[]=new int[n];//declaration
        System.out.println("Enter elements: ");
        for(int i=0;i<a.length;i++)//initialization
        {
            a[i]=sc.nextInt();
        }
        System.out.println("Stored elements are: ");
       for(int i:a)//to show stored array
       {
            System.out.println(i);

       }
        for (int i = 1; i <a.length-1; i++) {
            if (a[i]>a[i-1] && a[i]>a[i+1]){
                System.out.println("Peak Elements is: ");
                System.out.println(a[i]);
                break;
            }
        }
    }
}
