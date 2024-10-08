package StreamAPI.Interview_Prep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Employees {
    private int id;
    private String name;
    private double salary;

    // Constructor, getters, and setters

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Employees employee = (Employees) o;
//        return id == employee.id &&
//                Double.compare(employee.salary, salary) == 0 &&
//                name.equals(employee.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, salary);
//    }
}

public class Prep2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 5000),
                new Employee(2, "Jane", 6000),
                new Employee(1, "John", 5000), // Duplicate
                new Employee(3, "Jack", 7000)
        );

        //Since class has the Hashcode and equals overridden methods we can use distinct
//        List<Employee> distinctEmployees = employees.stream()
//                .distinct()
//                .collect(Collectors.toList());
//
//        distinctEmployees.forEach(emp -> System.out.println(emp.getName()));


        // Use a Set to track seen employees if the class hasn't implemented Hashcode and distinct methods
        Set<Integer> seenIds = new HashSet<>();

       // System.out.println(seenIds.add(11)); //true or false if added success

        List<Employee> distinctEmployees = employees.stream()
                .filter(emp -> seenIds.add(emp.getId()))  // Only add if not already in the set
                .collect(Collectors.toList());

        distinctEmployees.forEach(emp -> System.out.println(emp.getName()));
    }
}
