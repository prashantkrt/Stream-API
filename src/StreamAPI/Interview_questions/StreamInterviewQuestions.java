package StreamAPI.Interview_questions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamInterviewQuestions {
    public static void main(String[] args) {

//      ** group and count the number of occurrences **

        // First Way
        // with formatting
        String str = "Apple,Orange,Apple,Orange";

        Map<String, Long> result = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        // [Apple-2, Orange-2]
        String output = result.entrySet().stream()
                .map(e -> e.getKey() + "-" + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(output);

        //Second way
        String ss = "Apple,Orange,Apple,Orange";

        Map<String, Long> res = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        //{Apple=2, Orange=2}
        System.out.println(res);
        System.out.println();

        result.forEach((key, value) -> System.out.println(key + "-" + value));


        // Third way
//    Map<String, Integer> result = Arrays.stream(str.split(","))
//            .collect(Collectors.toMap(
//                    s -> s,
//                    s -> 1,
//                    Integer::sum
//            ));
//
//        result.forEach((key, value) -> System.out.println(key + "-" + value));


//    Map<String, Integer> result = Arrays.stream(str.split(","))
//            .collect(Collectors.toMap(
//                    s -> s,
//                    s -> 1,
//                    Integer::sum
//            ));
//
//        result.forEach((key, value) -> System.out.println(key + "-" + value));

        Map<String, Integer> map = Arrays.stream(str.split(",")).collect(Collectors.toMap(i -> i, i -> 1, (a, b) -> a + b));
        System.out.println(map);


//     **  FirstNonRepeatedCharacter **

        String input = "swiss";
       IntStream chars = input.chars(); //gives InputStream
        // .mapToObj(c -> (char) c) converts each int to its corresponding char, resulting in a stream of characters: ['s', 'w', 'i', 's', 's'].
// The input.chars() method in Java's String class returns an IntStream of the characters in the string.
// Using LinkedHashMap to maintain the order

//        Character firstNonRepeatedChar = input.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);

//
//        Character firstNonRepeatedChar = input.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue() == 1)
//                .map(Map.Entry::getKey) or  .map(entry -> entry.getKey())
//                .findFirst()
//                .orElse(null);

        Character firstNonRepeatedChar = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, () -> new LinkedHashMap<>(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println(firstNonRepeatedChar);


        long number = 121234;

        Optional<Integer> firstNonRepeatedDigit = Long.toString(number).chars()  // Stream of int (ASCII values)
                .mapToObj(c -> (char) c)  // Convert ASCII values to Character
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())) // Count occurrences
                .entrySet().stream()  // Stream of Map.Entry<Character, Long>
                .filter(entry -> entry.getValue() == 1)  // Filter for non-repeated digits
                .map(Map.Entry::getKey)  // Get the Character from the entry
                //.map(c -> Character.getNumericValue(c))  // Convert Character to Integer
                .map(c -> c - '0')  // Convert Character to Integer using ASCII
                .findFirst(); // Find the first non-repeated digit

        // Print the result
        firstNonRepeatedDigit.ifPresentOrElse(
                digit -> System.out.println("First non-repeated digit: " + digit),
                () -> System.out.println("No non-repeated digit found.")
        );
//        int number = 121234;
//
//        Optional<Character> firstNonRepeatedDigit = Integer.toString(number).chars()  // Stream of int (ASCII values)
//                .mapToObj(c -> (char) c)  // Convert to Character
//                .collect(LinkedHashMap::new, (map, c) -> map.put(c, map.getOrDefault(c, 0) + 1), LinkedHashMap::putAll) // Count occurrences
//                .entrySet().stream()  // Stream of Map.Entry<Character, Integer>
//                .filter(entry -> entry.getValue() == 1)  // Filter for non-repeated digits
//                .map(Map.Entry::getKey)  // Get the Character from the entry
//                .findFirst();  // Find the first non-repeated character
//
//        // Print the result
//        firstNonRepeatedDigit.ifPresentOrElse(
//                digit -> System.out.println("First non-repeated digit: " + digit),
//                () -> System.out.println("No non-repeated digit found.")
//        );


        // just playing around
//        List<Employee> employees = IntStream.rangeClosed(1, 10)
//                .mapToObj(i -> new Employee(i, "employee " + i)).collect(Collectors.toList());
//        log.info("EmployeeService:getEmployees find all employees from system  {}", new ObjectMapper().writeValueAsString(employees));
//        return employees;


        // Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        List<Integer> myNewList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        myNewList.stream()
                .map(s -> s + "") // Convert integer to String
                .filter(s -> s.startsWith("1"))
                .forEach(System.out::println);

        List<Integer> newList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        newList.stream()
                .map(s -> s + "") // Convert integer to String
                .filter(s -> s.startsWith("1"))
                .map(Integer::parseInt)
                .forEach(System.out::println);

        /* or can also try below method */

        List<String> list = Arrays.stream(new int[]{1, 2, 3, 4})
                .boxed()  // Converts IntStream to Stream<Integer>
                .map(s -> s + "")
                .filter(s -> s.startsWith("1"))
                .collect(Collectors.toList());

        IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).map(i -> i + 2).boxed().toList();

        Arrays.stream(new int[]{1, 2, 3, 4}).map(i -> i * 2)
                .boxed()   // Box the primitive int to Integer
                .toList();

        System.out.println(list);


        //How to find duplicate elements in a given integers list in java using Stream functions?
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
        Set<Integer> set = new HashSet();
        myList.stream()
                .filter(n -> !set.add(n))
                .forEach(System.out::println);

        //Given a list of integers, find the total number of elements present in the list using Stream functions?
        List<Integer> myNList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
        long count = myList.stream()
                .count();
        System.out.println(count);

        /* or can also try below line code */
        Arrays.stream(new int[]{1, 2, 3, 4, 5}).boxed().count();


        //Given a String, find the first repeated character in it using Stream functions?
        // String input = "Java Articles are Awesome";

        Character resultNew = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);


        //Examples of whitespace characters:
        //' ' (space)
        //'\t' (tab)
        //'\n' (newline)
        //'\r' (carriage return)
        input = "Java Articles are Awesome";

        //confusing ignore it
        //String sanitizedInput = input.replaceAll("\\s", ""); we can also do this way
        Character firstRepeatedChar = input.chars()
                .mapToObj(c -> Character.toLowerCase((char) c)) // Convert int to char
                .filter(c -> !Character.isWhitespace(c)) // Ignore whitespaces (optional)
                .filter(new HashSet<>()::add) // Filter characters that can't be added to the set
                .findFirst()
                .orElse(null);

        System.out.println("First repeated character: " + firstRepeatedChar);

        //char lower = Character.toLowerCase('A'); // Converts 'A' to 'a'
        //char upper = Character.toUpperCase('b'); // Converts 'b' to 'B'
        //
        //System.out.println(lower); // Output: a
        //System.out.println(upper); // Output: B
        //String input = "Java Programming";
        //
        // Convert to lowercase
        //String lower = input.toLowerCase(); // "java programming"
        //
        // Convert to uppercase
        //String upper = input.toUpperCase(); // "JAVA PROGRAMMING"
        //
        //System.out.println(lower); // Output: java programming
        //System.out.println(upper); // Output: JAVA PROGRAMMING

        // 2nd way
        Set<Character> seenCharacters = new HashSet<>();

        resultNew = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !Character.isWhitespace(c))
                .filter(c -> !seenCharacters.add(c))
                .findFirst()
                .orElse(null);


        //Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

//        List<Integer> list = Arrays.stream(nums)
//                .boxed()
//                .collect(Collectors.toList());
//        Set<Integer> set = new HashSet<>(list);
//        if(set.size() == list.size()) {
//            return false;
//        }
//        return true;

        /* or can also try below way */
     /*
          Set<Integer> setData = new HashSet<>();
          return Arrays.stream(nums)
                .anyMatch(num -> !setData.add(num));
    */


        // how to convert string into char array and pass it to stream

//        String str = "Hello World";
//
//        // Convert string to char array
//        char[] charArray = str.toCharArray();
//
//        // Convert char array to stream
//        Stream<Character> charStream = Arrays.stream(charArray)
//                .mapToObj(c -> (char) c); // Convert each char to Character
//
//        // Example operation on the stream
//        charStream.forEach(System.out::println);


//        String str = "swiss";
//
//        // Convert string to character stream and group by character with their count
//        Character firstNonRepeatedChar = str.chars()
//                .mapToObj(c -> (char) c) // Convert int stream to character stream
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // Group by char and count occurrences
//                .entrySet().stream()
//                .filter(entry -> entry.getValue() == 1) // Filter only entries with a count of 1
//                .map(Map.Entry::getKey) // Get the character
//                .findFirst() // Find the first occurrence
//                .orElse(null); // If no non-repeated character exists
//
//        if (firstNonRepeatedChar != null) {
//            System.out.println("First non-repeated character: " + firstNonRepeatedChar);
//        } else {
//            System.out.println("No non-repeated character found.");
//        }


        // we can also use boxed instead of mapToObj()

        // Convert string to character stream and group by character with their count
//        Character firstNonRepeatedChar = str.chars()
//                .boxed() // Convert IntStream to Stream<Integer>
//                .collect(Collectors.groupingBy(i -> (char) i.intValue(), LinkedHashMap::new, Collectors.counting())) // Group by char and count occurrences
        //.collect(Collectors.groupingBy(i -> (char) i, LinkedHashMap::new, Collectors.counting())) // Group by char and count occurrences
//                .entrySet().stream()
//                .filter(entry -> entry.getValue() == 1) // Filter only entries with a count of 1
//                .map(Map.Entry::getKey) // Get the character
//                .findFirst() // Find the first occurrence
//                .orElse(null); // If no non-repeated character exists
//


    }

}

