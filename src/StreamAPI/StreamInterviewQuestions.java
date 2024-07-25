package StreamAPI;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    }


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
}
