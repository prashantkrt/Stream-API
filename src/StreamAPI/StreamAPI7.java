package StreamAPI;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI7 {
    public static void main(String[] args) {
        /*
         * Collectors.toMap() and Collectors.groupingBy() are both used in Java's Stream API for collecting elements from a stream into a collection,
         * but they serve different purposes and have different functionalities.
         * */


       /* Collectors.toMap()
        Purpose: This collector is used to create a Map from the elements of a stream.
        It requires you to specify how to derive the keys and values for the map.

        Parameters:

        Key Mapper: A function that extracts a key from each element.
        Value Mapper: A function that extracts a value from each element.
        Merge Function (optional): A function to resolve collisions when the same key is encountered. This function defines what to do with existing and new values.
        */

        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date");

        // Create a Map where key is the fruit name and value is its length
        Map<String, Integer> fruitLengthMap = fruits.stream()
                .collect(Collectors.toMap(
                        fruit -> fruit,                   // Key mapper: fruit name
                        String::length,                   // Value mapper: length of the fruit name
                        (existing, replacement) -> existing, // Merge function: keep existing if duplicate key
                        TreeMap::new                        //(optional) Map factory: create a TreeMap
                ));

        System.out.println(fruitLengthMap); // Output: {banana=6, apple=5, cherry=6, date=4}

    /*
        Purpose: This collector is used to group elements of a stream by a classifier function.
        It produces a Map where the keys are the result of applying a classifier function to the input elements, and the values are Lists of items that share the same key.

        Parameters:
        Classifier Function: A function that extracts a key from each element.
        Downstream Collector (optional): A collector that will be applied to the values of each group (e.g., counting, averaging, or collecting into a list,toList().
        Map Factory (optional): A supplier that creates the Map implementation to use (e.g., HashMap, TreeMap).
    */

        fruits = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");

        // Group fruits by their initial character using a LinkedHashMap
        Map<Character, List<String>> fruitsByInitial = fruits.stream()
                .collect(Collectors.groupingBy(
                        fruit -> fruit.charAt(0),  // Classifier: first character of the fruit
                        LinkedHashMap::new,        // Map factory: create a LinkedHashMap
                        Collectors.toList()        // Downstream collector: collect into a list
                ));

        System.out.println(fruitsByInitial);
        // Output: {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}


        /*
        Method Signature

        toMap() :
        public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(
                Function<? super T, ? extends K> keyMapper,
                Function<? super T, ? extends U> valueMapper,
                BinaryOperator<U> mergeFunction,
                Supplier<Map<K, U>> mapFactory

         Map<String, Integer> fruitLengthMap = fruits.stream()
               .collect(Collectors.toMap(
                fruit -> fruit,                   // Key mapper
                String::length,                   // Value mapper
                (existing, replacement) -> existing, // Merge function
                HashMap::new                       // Map factory
              ));


          groupingBy() :
          public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(
                Function<? super T, ? extends K> classifier,
                Collector<? super T, ?, D> downstream,
                Supplier<Map<K, A>> mapFactory
            )

            Map<Character, List<String>> fruitsByInitial = fruits.stream()
                .collect(Collectors.groupingBy(
                    fruit -> fruit.charAt(0),       // Classifier
                    Collectors.toList(),            // Downstream collector
                    LinkedHashMap::new              // Map factory
                ));

          */
    }
}
