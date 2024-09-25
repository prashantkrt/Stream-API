package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Java Stream Intermediate Operations

- filter()
  Stream.of("B", "A", "C" , "B")
        .filter(s -> s.equals("B"))

- map()
  Stream.of("B", "A", "C" , "B")
        .map(s -> s.toLowerCase())

- flatMap()
  // flattens the multiple Stream to single Stream

        Stream.of(
        Arrays.asList("B", "A"),
        Arrays.asList("C", "B")
        )
        .flatMap(l -> l.stream())

- distinct()
   Stream.of("B", "A", "C" , "B")
        .distinct()

- sorted()
   Stream.of("B", "A", "C" , "B")
        .sorted()

Using peek without any terminal operation does nothing.
-  peek()
     Stream.of("B", "A", "C" , "B")
        .peek(System.out::print)

     Stream.of("B", "A", "C" , "B")
        .peek(System.out::print)
        .forEach(
     returns a Stream with the elements "B", "A", "C" and "B" but, when consumed in its entirety, will print out the text "BACB" as a side effect.

- limit()
     Stream.of("B", "A", "C" , "B")
        .limit(2)

- skip()
   Stream.of("B", "A", "C" , "B")
        .skip(1)
   returns a Stream with the elements "A", "C" and "B" because the first element in the stream will be skipped.
*/


/*
Java Stream Terminal Operations
- forEach => void
- count => Integer
- reduce => Integer
- collect
- anyMatch => Boolean
    example 1 ⇒ boolean result = stream.anyMatch(predicate);
    example 2 ⇒
      List<Integer> numbers = Arrays.asList(3, 7, 9, 12, 5);

        // Check if any number is greater than 10
        boolean isAnyGreaterThan10 = numbers.stream()
                                            .anyMatch(num -> num > 10);

        System.out.println("Is any number greater than 10? " + isAnyGreaterThan10);
    }
- allMatch => Boolean
      List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);

        // Check if all numbers are even
        boolean allEven = numbers.stream()
            .allMatch(num -> num % 2 == 0);  // Predicate: Is the number even?

        System.out.println("Are all numbers even? " + allEven); // Output: true
- noneMatch => Boolean
      List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);

        // Check if none of the numbers are even
        boolean noneEven = numbers.stream()
            .noneMatch(num -> num % 2 == 0);  // Predicate: Is the number even?

        System.out.println("Are none of the numbers even? " + noneEven);  // Output: true

- findFirst => Optional<?>
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Find the first name in the list
        Optional<String> firstName = names.stream()
            .findFirst();

        // If the first element is present, print it
        firstName.ifPresent(name -> System.out.println("First name: " + name));  // Output: First name: Alice
    }
- findAny => Optional<?>
    any element can be returned , it is non-deterministic, meaning it can return any element
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Find any name from the list
        Optional<String> anyName = names.stream()
            .findAny();

        // If an element is found, print it
        anyName.ifPresent(name -> System.out.println("Found name: " + name));
    }
- min
- max
- toArray
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Convert the stream to a String array using a lambda expression
        String[] namesArray = names.stream().toArray(size -> new String[size]);

        // Print the array
        System.out.println(Arrays.toString(namesArray));

       List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Convert the stream to an Integer array using a lambda expression
        Integer[] numbersArray = numbers.stream().toArray(size -> new Integer[size]);

        // Print the array
        System.out.println(Arrays.toString(numbersArray));  // Output: [1, 2, 3, 4, 5]
*/

public class StreamAPI1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 1, 2, 6, 7, 9, 10, 2, 3);

        // sum of even number * 2
        int sum = list.stream().filter(i -> i % 2 == 0).map(i -> i * 2).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        // sum of odd number * 2
        int sumOdd = list.stream().filter(i -> i % 2 != 0).map(i -> i * 2).reduce(0, Integer::sum);

        // sum of only even number
        int evenSum = list.stream().filter(i -> i % 2 == 0).mapToInt(n -> n).sum();
        System.out.println(evenSum);

        // sum of only odd number
        int oddSum = list.stream().filter(i -> i % 2 != 0).mapToInt(n -> n).reduce(0, (a, b) -> a + b);

        // distinct element
        list = list.stream().distinct().toList();
        System.out.println(list);

        // stream is an interface
        // once we have used the stream, next time it cannot be used
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
        int max = ll.stream().max((i, j) -> i > j ? 1 : -1).get();
        System.out.println(max);
        int max2 = ll.stream().max((a, b) -> a - b).get();
        System.out.println(max2);

        // peek() API to debug the Stream operations and log Stream elements as they are processed.

        //Stream.peek() without any terminal operation does nothing.
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
        //does nothing
        nameStream.peek(System.out::println); // the result will be ignored

        List<Integer> demo
                = Arrays.asList(0, 2, 4, 6, 8, 10);
        // Using peek without any terminal
        // operation does nothing
        demo.stream().peek(System.out::println); // the result will be ignored


        System.out.println();
        List<Integer> newList
                = Arrays.asList(0, 2, 4, 6, 8, 10);
        // Using peek with the forEach() method
        // which is a terminal operation
        newList.stream()
                .peek(System.out::println)
                .forEach(x -> {
                });


        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> newIntegerList = list.stream()
                .peek(System.out::println) // now it will print the element the way it's getting processed
                .collect(Collectors.toList());

        System.out.println(newIntegerList);

        Stream.of("B", "A", "C", "B")
                .peek(System.out::println)
                .forEach(x -> {
                });

        Stream.of(1, 2, 3, 4, 5)
                .peek(x -> {
                }).forEach(System.out::println);

        Stream.of(Arrays.asList(1, 2, 3, 4, 5)).flatMap(i -> i.stream()).collect(Collectors.toList());
        Stream.of(Arrays.asList(1, 2, 3, 4, 5)).flatMap(i -> i.stream()).filter(i -> i % 2 != 0).forEach(i -> {
        });

        // just random things :)
        List<Integer> ml = Stream.of(1, 2, 3, 4).filter(i -> i % 2 == 0).collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet().stream().filter(c -> c.getValue() > 1).map(m -> m.getKey()).toList();


//        The peek() method in Java's Stream API is used to perform an action (like logging or debugging) on each element in the stream as it passes through, without modifying the stream. It allows you to "peek" at the elements while they are being processed.
//
//        Key Points:
//
//        Side effects only: peek() is meant for side effects, like printing, logging, or debugging.
//        No modification: It doesn't change the stream's elements; it just looks at them.
//        Intermediate operation: It doesn't trigger stream processing by itself; it works within the stream's flow.

        List<String> name = Arrays.asList("Alice", "Bob", "Charlie");

        names.stream()
                .filter(n -> n.startsWith("A")) // Only "Alice" passes through
                .peek(n -> System.out.println("Filtered: " + n)) // Prints "Filtered: Alice"
                .collect(Collectors.toList());


        //flatMap example:-

        List<List<String>> namesList = Arrays.asList(
                Arrays.asList("Alice", "Bob"),
                Arrays.asList("Charlie", "David"),
                Arrays.asList("Eve", "Frank")
        );

        // Flatten the list of lists into a single list of names
        List<String> flatList = namesList.stream()
//                .flatMap(List::stream)  // Flatten the inner lists into a single stream
//                .flatMap(Collection::stream)
                .flatMap(myList1 -> myList1.stream())
                .collect(Collectors.toList());

        System.out.println(flatList);


        System.out.println("End of content");
    }
}
