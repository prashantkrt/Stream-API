package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 1, 2, 6, 7, 9, 10, 2, 3);

        // sum of even number * 2
        int sum = list.stream().filter(i -> i % 2 == 0).map(i -> i * 2).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        //sum of only even number
        int evenSum = list.stream().filter(i -> i % 2 == 0).mapToInt(n -> n).sum();
        System.out.println(evenSum);

        //distinct element
        list = list.stream().distinct().toList();
        System.out.println(list);

        // stream is an interface
        // once we have used the stream , next time it cannot be used
        Stream<Integer> s1 = list.stream();
        s1.forEach(i -> System.out.print(i + " "));

        Stream<Integer> s2 = list.stream();
        Stream<Integer> s3 = s2.filter(i -> i != 0);
        s3.forEach(i -> {
            return;
        });

        List<Integer> myList = List.of(1, 2, 3, 4, 5, 6, 7);
        Long total = myList.stream().count();
        System.out.println();
        System.out.println(total);

        List<Integer> number = Arrays.asList(2, 3, 4, 5);
        List<Integer> square = number.stream().map(x -> x * x).toList();

        List<String> names = Arrays.asList("Reflection", "Collection", "Stream");
        //List<String> result = names.stream().filter(s->s.startsWith("S")).collect(Collectors.toList());
        List<String> result = names.stream().filter(s -> s.startsWith("S")).toList();

        List<String> names2 = Arrays.asList("Reflection", "Collection", "Stream");
        List<String> result2 = names.stream().sorted().collect(Collectors.toList());

        List<Integer> myNumber = Arrays.asList(2, 3, 4, 5, 3);
        Set<Integer> squares = myNumber.stream().map(x -> x * x).collect(Collectors.toSet());

        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream().map(x -> x * x).forEach(y -> System.out.println(y));

        //Here answer variable is assigned 0 as the initial value and i is added to it.
        List<Integer> myNumbers = Arrays.asList(2, 3, 4, 5);
        int even = myNumbers.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
        System.out.println(even);//2+4 = 6

        int evenSquare = myNumbers.stream().filter(x -> x % 2 == 0).mapToInt(i -> i * i).sum();
        System.out.println(evenSquare);

        System.out.println();
        Stream.iterate(1, element -> element + 1)
                .filter(element -> element % 5 == 0)
                .limit(5)
                .forEach(i -> System.out.print(i + " "));

        System.out.println();
        List<Integer> l = List.of(1, 2, 3, 4);
        int sum2 = l.stream().filter(i -> i % 2 == 0).reduce(0, (a, b) -> (a + b));
        System.out.println(sum2);


        List<Integer> ll = List.of(1, 2, 3, 4);
        int max = ll.stream().max((i,j)->i>j?1:-1).get();
        System.out.println(max);
    }
}
