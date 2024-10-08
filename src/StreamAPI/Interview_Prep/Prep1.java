package StreamAPI.Interview_Prep;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    int id;
    String name;
    double age;

    Employee(int id, String name, double age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}


class Customer {
    int id;
    int age;
    double salary;
    String department;
    String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

public class Prep1 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Bablu", 34));
        employees.add(new Employee(2, "Bobi", 34));
        employees.add(new Employee(3, "aBob", 14));
        employees.add(new Employee(4, "Sunny", 90));
        employees.add(new Employee(5, "Srhha", 81));
        employees.add(new Employee(6, "KKSDX", 56));
        employees.add(new Employee(7, "Boby", 34));

        Double averageAge = employees.stream().filter(i -> i.getAge() > 12).mapToDouble(Employee::getAge).average().getAsDouble();

        employees.stream().map(i -> i.getAge()).reduce(0.0, (a, b) -> a + b);

        List<Integer> list = Arrays.asList(4, 1, 2, 6, 7, 9, 10, 2, 3);

        list.stream().filter(i -> i == i).reduce(0, (a, b) -> a + b);

        Double ans = list.stream().filter(i -> i % 2 == 0).mapToDouble(i -> i).sum();

        List<Integer> numbers = Arrays.asList(3, 5, 2, 8, 1);
        Optional<Integer> minNumber = numbers.stream()
                .min(Integer::compareTo);
        minNumber.ifPresent(System.out::println); // Output: 1


        Stream.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 3, 6, 89, 1, 10)).flatMap(i -> i.stream()).collect(Collectors.toList());


        employees.stream()
                .max(Comparator.comparing(Employee::getAge).reversed())
                .ifPresent(System.out::println);

        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .forEach(System.out::println);

    }

}
