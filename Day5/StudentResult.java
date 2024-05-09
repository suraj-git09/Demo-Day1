

import java.util.Scanner;

class School {
    private String name;
    
    public School(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

class Subject {
    private String name;
    private int marks;
    
    public Subject(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMarks() {
        return marks;
    }
}

class Student {
    private String name;
    private School school;
    private Subject[] subjects;
    
    public Student(String name, School school, Subject[] subjects) {
        this.name = name;
        this.school = school;
        this.subjects = subjects;
    }
    
    public void displayResult() {
        System.out.println("Student Name: " + name);
        System.out.println("School: " + school.getName());
        System.out.println("Subjects and Marks:");
        for (Subject subject : subjects) {
            System.out.println(subject.getName() + ": " + subject.getMarks());
        }
    }
}

public class StudentResult {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input student name
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        
        // Input school name
        System.out.print("Enter school name: ");
        String schoolName = scanner.nextLine();
        
        // Create a School object
        School school = new School(schoolName);
        
        // Input subjects and marks
        System.out.println("Enter subjects and marks:");
        Subject[] subjects = new Subject[3]; // Assuming 3 subjects
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter subject " + (i + 1) + " name: ");
            String subjectName = scanner.next();
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            int marks = scanner.nextInt();
            subjects[i] = new Subject(subjectName, marks);
        }
        
        // Create a Student object
        Student student = new Student(studentName, school, subjects);
        
        // Display the result
        student.displayResult();
        
        scanner.close();
    }
}
