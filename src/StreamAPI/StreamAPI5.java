package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Gfg {

    int roll;
    int marks;
    String name;

    Gfg(int roll, int marks, String name) {
        this.roll = roll;
        this.marks = marks;
        this.name = name;
    }

    int getRoll() {
        return roll;
    }

    int getMarks() {
        return marks;
    }

    String getName() {
        return name;
    }
}

public class StreamAPI5 {
    public static void main(String[] args) {

        Gfg[] arr = {new Gfg(10, 12, "ABC"), new Gfg(23, 56, "BCV")};
        double average = Arrays.stream(arr).mapToInt(g -> g.roll).average().getAsDouble();
        int max = Arrays.stream(arr).mapToInt(g -> g.marks).max().getAsInt();
        int min = Arrays.stream(arr).mapToInt(g -> g.marks).min().getAsInt();
        int sum = Arrays.stream(arr).mapToInt(g -> g.roll).sum();
        System.out.println(average);

        Map<Integer, Integer> map = Arrays.stream(arr).collect(Collectors.toMap(Gfg::getMarks, Gfg::getRoll));

        Map<Integer, List<Gfg>> map1 = Arrays.stream(arr).collect(Collectors.groupingBy(Gfg::getMarks));

    }
}
