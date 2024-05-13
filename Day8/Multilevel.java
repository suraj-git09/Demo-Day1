class Shape {
    public void display() {
       System.out.println("Display");
    }
 }
 class Rectangle extends Shape {
    public void area() {
       System.out.println("Area");
    }
 }
 class Cube extends Rectangle {
    public void volume() {
       System.out.println("Volume");
    }
 }
 public class Multilevel {
    public static void main(String[] args) {
       Cube cube = new Cube();
       cube.display();
       cube.area();
       cube.volume();
    }
 }