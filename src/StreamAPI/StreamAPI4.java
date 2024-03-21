package StreamAPI;

import java.util.Arrays;
import java.util.List;

public class StreamAPI4 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //this will use the single thread for performing the operations
        int result = list.stream().filter(i -> i % 3 == 0).map(i -> i + 3).reduce(0, (a, b) -> a + b);
        System.out.println(result);

        //splits over multiple threads
        //multiple sessions
        result = list.parallelStream().filter(i -> i % 2 == 0).map(i -> i * 2).reduce(0, (a, b) -> a + b);
        System.out.println(result);
    }
}
