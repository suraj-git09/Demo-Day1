import java.util.Scanner;
public class OverloadingFuncArea {
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter length and width of the rectangle:");
        double length = scanner.nextDouble();
        double width = scanner.nextDouble();
        double rectangleArea = calculateAreaRectangle(length, width);

        System.out.println("Enter side length of the square:");
        double side = scanner.nextDouble();
        double squareArea = calculateAreaSquare(side);

        System.out.println("Enter radius of the circle:");
        double radius = scanner.nextDouble();
        double circleArea = calculateAreaCircle(radius);

        System.out.println("Area of Rectangle: " + rectangleArea);
        System.out.println("Area of Square: " + squareArea);
        System.out.printf("Area of Circle: %.2f\n", circleArea);

        scanner.close();
    }

    // Method to calculate area of a rectangle
    public static double calculateAreaRectangle(double length, double width) {
        return length * width;
    }

    // Method to calculate area of a square (overloaded version)
    public static double calculateAreaSquare(double side) {
        return side * side;
    }

    // Method to calculate area of a circle (overloaded version)
    public static double calculateAreaCircle(double radius) {
        return Math.PI * radius * radius;
    }
}