package StreamAPI.Interview_questions;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class StreamInterviewQuestions {
    public static void main(String[] args) {

//      ** group and count the number of occurrences **

        // First Way
        // with formatting
        String str = "Apple,Orange,Apple,Orange";

        Map<String, Long> result = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        String output = result.entrySet().stream()
                .map(e -> e.getKey() + "-" + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(output);

        //Second way
        String ss = "Apple,Orange,Apple,Orange";

        Map<String, Long> res = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
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

        Map<String, Integer>  map= Arrays.stream(str.split(",")).collect(Collectors.toMap(i->i,i->1,(a,b)->a+b));
        System.out.println(map);


//     **  FirstNonRepeatedCharacter **

        String input = "swiss";
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
                .findFirst();  // Find the first non-repeated digit

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
    }
}

