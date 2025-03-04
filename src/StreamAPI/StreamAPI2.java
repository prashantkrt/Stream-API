package StreamAPI;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI2 {
    int id;
    String name;
    float price;

    public StreamAPI2(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static void main(String[] args) {
        List<StreamAPI2> list = new ArrayList<StreamAPI2>();
        list.add(new StreamAPI2(1, "HP Laptop", 25000f));
        list.add(new StreamAPI2(2, "Dell Laptop", 30000f));
        list.add(new StreamAPI2(3, "Lenovo Laptop", 28000f));
        list.add(new StreamAPI2(4, "Sony Laptop", 28000f));
        list.add(new StreamAPI2(5, "Apple Laptop", 90000f));

        List<Float> list2 = list.stream().filter(p -> p.price > 30000)// filtering data
                .map(p -> p.price)        // fetching price
                .toList(); // collecting as a list
        list2.forEach(i -> System.out.print(i + " "));

        System.out.println();

        Float totalPrice = list.stream().map(product -> product.price).reduce(0.0f, Float::sum);   // accumulating price
        System.out.println(totalPrice);

        StreamAPI2 product = list.stream().max((p1, p2) -> p1.price > p2.price ? 1 : -1).get();
        System.out.println(product.price);

        list.stream().max((p1, p2) -> (int) (p1.price - p2.price)).get();
        System.out.println(product.price);

        //list.stream() creates a Stream of Product objects, not a primitive stream like IntStream or DoubleStream.
        //max() without a comparator wouldn’t know how to compare products — it only works directly for primitives.
        StreamAPI2 product1 = list.stream()
                .max(Comparator.comparingDouble(p -> p.price))
                .get();
        System.out.println(product.price);

        StreamAPI2 product2 = list.stream()
                .max(Comparator.comparing(p -> p.price))
                .get();
        System.out.println(product2.price);

        long count = list.stream().filter(p -> p.price < 30000).count();
        System.out.println(count);

        //converting to the map
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(p -> p.id, p -> p.name));
        System.out.println(map);


        // Comparator.comparingDouble is meant for primitive double values,
        // so it avoids unnecessary boxing and unboxing that would happen if you used Double objects.

        double[] arr = {1.5, 3.7, 2.2, 4.8};

        // Find the maximum value using comparingDouble
        double max = Arrays.stream(arr)
                .boxed() // Box the primitive double to Double
                .max(Comparator.comparingDouble(i -> i))
                .get();

        System.out.println("Max value: " + max);
    }
}
