package Day12.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Using ArrayList to store employees
        List<Employee> employees = new ArrayList<>();

        // Adding employees to the list
        employees.add(new Employee(1, "Suraj", "Engineering"));
        employees.add(new Employee(2, "Ayush", "Marketing"));
        employees.add(new Employee(3, "Mike", "Sales"));

        // Displaying all employees
        System.out.println("Employees in the list:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Using HashMap to store employees by their ID
        Map<Integer, Employee> employeeMap = new HashMap<>();

        // Adding employees to the map
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }

        // Displaying employees by their ID
        System.out.println("\nEmployees in the map:");
        for (Integer id : employeeMap.keySet()) {
            System.out.println("ID: " + id + " -> " + employeeMap.get(id));
        }

        // Finding an employee by ID
        int searchId = 2;
        Employee foundEmployee = employeeMap.get(searchId);
        System.out.println("\nEmployee found with ID " + searchId + ": " + foundEmployee);

        // Updating an employee's department
        if (foundEmployee != null) {
            foundEmployee.setDepartment("Human Resources");
            employeeMap.put(searchId, foundEmployee); // Update the map with the new department
        }

        // Displaying the updated employee data
        System.out.println("\nUpdated employee data:");
        for (Integer id : employeeMap.keySet()) {
            System.out.println("ID: " + id + " -> " + employeeMap.get(id));
        }

        // Removing an employee by ID
        int removeId = 3;
        Employee removedEmployee = employeeMap.remove(removeId);
        System.out.println("\nEmployee removed with ID " + removeId + ": " + removedEmployee);

        // Displaying remaining employees in the map
        System.out.println("\nRemaining employees in the map:");
        for (Integer id : employeeMap.keySet()) {
            System.out.println("ID: " + id + " -> " + employeeMap.get(id));
        }
    }
}
