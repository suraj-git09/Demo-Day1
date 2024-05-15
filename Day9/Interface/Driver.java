package Day9.Interface;

public class Driver {
    public static void main(String[] args) {
        Student student=new Student(12,"Suraj");
        Student student1=new Student(11,"Ayush");
        student.display();
        System.out.println(student.showRollNo());
        System.out.println(student.compareTo(student1));
    }
}
