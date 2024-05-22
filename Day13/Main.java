package Day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ayush", 85.5));
        students.add(new Student(2, "Bob", 92.0));
        students.add(new Student(3, "Charlie", 78.0));
        students.add(new Student(4, "Abhishek", 65.5));
        students.add(new Student(5, "Eva", 88.0));

        // 1. Filter students with grades above 80
        List<Student> highGrades = students.stream()
                                           .filter(student -> student.getGrade() > 80)
                                           .collect(Collectors.toList());
        System.out.println("Students with grades above 80:");
        highGrades.forEach(System.out::println);

        // 2. Sort students by grade in descending order
        List<Student> sortedByGrade = students.stream()
                                              .sorted((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade()))
                                              .collect(Collectors.toList());
        System.out.println("\nStudents sorted by grade (descending):");
        sortedByGrade.forEach(System.out::println);

        // 3. Map students to their names
        List<String> studentNames = students.stream()
                                            .map(Student::getName)
                                            .collect(Collectors.toList());
        System.out.println("\nStudent names:");
        studentNames.forEach(System.out::println);

        // 4. Get the average grade of all students
        double averageGrade = students.stream()
                                      .mapToDouble(Student::getGrade)
                                      .average()
                                      .orElse(0.0);
        System.out.println("\nAverage grade of students: " + averageGrade);

        // 5. Count the number of students with grades below 70
        long countLowGrades = students.stream()
                                      .filter(student -> student.getGrade() < 70)
                                      .count();
        System.out.println("\nNumber of students with grades below 70: " + countLowGrades);
    }
}
