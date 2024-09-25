package StreamAPI;

import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream.iterate(1, element -> element + 1)
                .limit(5) // post all the iteration
                .filter(element -> element % 5 == 0) // it will come to filter now the value of element is 5
                .forEach(i -> System.out.print(i + " "));
    }
}
