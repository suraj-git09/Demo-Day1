import java.util.Scanner;
public class SecndLarNo {
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the Elements");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Entered Elements are");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("Entered Elements are");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Largest Element is " + arr[0]);
        for (int i = 0; i < n; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("2nd Largest Element is " + arr[i + 1]);
                break;
            }
        }
    }
}

