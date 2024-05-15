package Day9;

class Student implements Comparable<Student> {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public int compareTo(Student other) {
        // Compare the marks of this student with the marks of the other student
        if (this.marks > other.getMarks()) {
            return 1; // Marks of this student are higher
        } else if (this.marks < other.getMarks()) {
            return -1; // Marks of this student are lower
        } else {
            return 0; // Marks of both students are equal
        }
    }
}

public class Interface {
    public static void main(String[] args) {
        Student student1 = new Student("Alice", 85);
        Student student2 = new Student("Bob", 75);
        Student student3 = new Student("Charlie", 85);

        // Compare marks of student1 with student2
        int comparison1 = student1.compareTo(student2);
        printComparisonResult(comparison1);

        // Compare marks of student1 with student3
        int comparison2 = student1.compareTo(student3);
        printComparisonResult(comparison2);
    }

    public static void printComparisonResult(int comparison) {
        if (comparison > 0) {
            System.out.println("High");
        } else if (comparison < 0) {
            System.out.println("Less");
        } else {
            System.out.println("Equal");
        }
    }
}
