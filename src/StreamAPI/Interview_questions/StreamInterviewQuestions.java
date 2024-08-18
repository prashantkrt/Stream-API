package StreamAPI.Interview_questions;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class StreamInterviewQuestions {
    public static void main(String[] args) {
//        String str = "Apple,Orange,Apple,Orange";
//
//        Map<String, Long> result = Arrays.stream(str.split(","))
//                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
//
//        String output = result.entrySet().stream()
//                .map(e -> e.getKey() + "-" + e.getValue())
//                .collect(Collectors.joining(", ", "[", "]"));
//
//        System.out.println(output);

        //second way
        String str = "Apple,Orange,Apple,Orange";

        Map<String, Long> result = Arrays.stream(str.split(","))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        result.forEach((key, value) -> System.out.println(key + "-" + value));


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

        //map= Arrays.stream(str.split(",")).collect(Collectors.toMap(i->i,i->1,(a,b)->a+b));


        // FirstNonRepeatedCharacter

        String input = "swiss";
// The input.chars() method in Java's String class returns an IntStream of the characters in the string.
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
    }
}

