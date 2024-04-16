package StreamAPI;

import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream.iterate(1, element -> element + 1)
                .limit(5)
                .filter(element -> element % 5 == 0)
                .forEach(i -> System.out.print(i + " "));
    }
}
