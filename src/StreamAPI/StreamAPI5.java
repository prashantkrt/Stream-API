package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {

    int roll;
    int marks;
    String name;

    Student(int roll, int marks, String name) {
        this.roll = roll;
        this.marks = marks;
        this.name = name;
    }

    int getRoll() {
        return roll;
    }

    int getMarks() {
        return marks;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "roll=" + roll +
                ", marks=" + marks +
                ", name='" + name + '\'' +
                '}';
    }
}

public class StreamAPI5 {
    public static void main(String[] args) {

        Student[] arr = {new Student(10, 12, "ABC"), new Student(23, 56, "BCV")};
        double average = Arrays.stream(arr).mapToInt(g -> g.roll).average().getAsDouble();
        double average1 = Arrays.stream(arr).map(g -> g.roll).collect(Collectors.averagingDouble(Double::valueOf));
//        double average1 = Arrays.stream(arr)
//                .map(g -> g.roll)
//                .collect(Collectors.averagingDouble(value -> Double.valueOf(value)));
        int max = Arrays.stream(arr).mapToInt(g -> g.marks).max().getAsInt();
        //or
        max = Arrays.stream(arr).map(i -> i.marks).max((i, j) -> i > j ? 1 : -1).get();
        int min = Arrays.stream(arr).mapToInt(g -> g.marks).min().getAsInt();
        int sum = Arrays.stream(arr).mapToInt(g -> g.roll).sum();
        Arrays.stream(arr).mapToInt(g -> g.roll).average();
        //int sum = Arrays.stream(arr).map(g -> g.roll).sum(); wont work
        System.out.println(average);

        Map<Integer, Integer> map = Arrays.stream(arr).collect(Collectors.toMap(Student::getMarks, Student::getRoll));
        System.out.println(map);

        Map<Integer, List<Student>> map1 = Arrays.stream(arr).collect(Collectors.groupingBy(Student::getMarks));
        System.out.println(map1);
        Integer [] arr1 = new Integer[]{1,2,3,4};
        Arrays.stream(arr1).map(i -> i).collect(Collectors.toList());
        int [] arr2 = new int[]{1,2,3,4};
        Arrays.stream(arr2).boxed().collect(Collectors.toList());
        Arrays.stream(arr2).map(i->i).boxed().collect(Collectors.toList());
        Arrays.stream(arr2).boxed().map(i->i).collect(Collectors.toList());

    }
}
