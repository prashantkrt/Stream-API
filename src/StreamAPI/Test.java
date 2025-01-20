package StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StudentDemo {

    private String name;
    private int age;

    public StudentDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

public class Test {
    public static void main(String[] args) {
        Stream.iterate(1, element -> element + 1)
                .limit(5) // post all the iteration
                .filter(element -> element % 5 == 0) // it will come to filter now the value of element is 5
                .forEach(i -> System.out.print(i + " "));


        List<StudentDemo> students = Arrays.asList(
                new StudentDemo("John", 20),
                new StudentDemo("Jane", 22),
                new StudentDemo("Jack", 19)
        );

        // Manually calculate the average age
        double averageAge = students.stream()
                .mapToInt(StudentDemo::getAge) // Convert to IntStream
                .average() // Get an OptionalDouble for the average
                .orElse(0); // Handle case where the list is empty

        System.out.println("Average Age: " + averageAge);


        students.stream()
                .mapToInt(StudentDemo::getAge) // Convert to IntStream as age is int
                .boxed()
                .collect(Collectors.toList());

        // or
        students.stream()
                .map(StudentDemo::getAge)
                .collect(Collectors.toList());



        String str = "Apple,Orange,Apple,Orange";

        Map<String, Long> result = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<Map.Entry<String, Long>> list = new ArrayList<>(result.entrySet());
        System.out.println(list);

        System.out.println(result.entrySet().stream().collect(Collectors.toList()));

        List<Map.Entry<String, Long>> collect = result.entrySet().stream().collect(Collectors.toList());

        String ss = "Apple,Orange,Apple,Orange";

        Map<String, Long> res = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(i->i,Collectors.counting()));
        System.out.println(res);
        //{Apple=2, Orange=2}
        Map<Object, List<String>> res1 = Arrays.stream(str.split(",")).collect(Collectors.groupingBy(s->s.equals("Apple")));
        System.out.println(res1); //{false=[Orange, Orange], true=[Apple, Apple]}
        //Instead use
        Map<Boolean, List<String>> r = Arrays.stream(str.split(",")).collect(Collectors.partitioningBy(s->s.equals("Apple")));
        System.out.println(r);
    }
}
