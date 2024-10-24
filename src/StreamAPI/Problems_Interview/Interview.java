package StreamAPI.Problems_Interview;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Interview {
    public static void main(String[] args) {

        /*
         * 1.) Remove Duplicates from String and return in the same order
         * */

        String str = "dabfcadef";
        str.chars().mapToObj(c -> (char) c)
                .distinct()
                .forEach(System.out::print);

        /*
         * 2.) Given a sentence, find the word that has the highest length.
         * */

        String s = "I am Interested12345 to grow in my organization";
        System.out.println();
        String ans = Arrays.stream(s.split(" ")).max((a, b) -> (a.length() - b.length())).get();
        System.out.println(ans);
        //or
        Arrays.stream(s.split(" ")).max(Comparator.comparing(String::length)).get();

        /*
         * 3.) Given a sentence, find the word that has the 2nd (nth) the highest length.
         * */

        s = "I am Interested12345 to grow in my organization";
        ans = Arrays.stream(s.split(" ")).sorted(Comparator.comparing(String::length).reversed()).skip(1).findFirst().get();
        System.out.println(ans);

        /*
         * 4.) Find the length of the longest word
         * */

        s = "I am Interested12345 to grow in my organization";
        Arrays.stream(s.split(" ")).mapToInt(String::length).max().getAsInt();
        Arrays.stream(s.split(" ")).map(String::length).max((a, b) -> (b - a)).ifPresent(System.out::println);
        Arrays.stream(s.split(" ")).map(String::length).max((a, b) -> (b - a)).get();

        /*
         * 5.) Find the 2nd highest length word in the given sentence
         * */

        s = "I am Interested12345 to grow in my organization";
        int result = Arrays.stream(s.split(" ")).map(i -> i.length()).sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(result);
        Arrays.stream(s.split(" ")).sorted(Comparator.comparing(String::length).reversed()).skip(1).map(String::length).findFirst().ifPresent(System.out::println);

        /*
         * 6.) Given a sentence, find the number of occurrences of each word
         * */

        str = "the quick brown fox jumps right over the little lazy dog little";
        Map<String, Long> map = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(map);
        //{over=1, the=2, quick=1, lazy=1, jumps=1, right=1, brown=1, dog=1, fox=1, little=2}

        /*
         * 7.)   Given word, find the occurrence of each character
         * */

        s = "Prashant";
        Map<Character, Long> map1 = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map1);
        //{P=1, a=2, r=1, s=1, t=1, h=1, n=1}

        /*
         * 8.) There is a list of Employees and Employee object has a filed called e-mail. Find the list of domains(gmail.com,yahoo.com) and the number of occurrences of each domain.
         * */

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", 28, "gmail.com"));
        employees.add(new Employee(2, "Jane Smith", 32, "yahoo.com"));
        employees.add(new Employee(3, "Mike Johnson", 25, "gmail.com"));
        employees.add(new Employee(4, "Emily Davis", 40, "yahoo.com"));
        employees.add(new Employee(5, "Chris Brown", 30, "gmail.com"));
        map = employees.stream().map(Employee::getDomain).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        // if we have the email instead of domain, then we can do this way!!
        // Using Stream API to find the domain occurrences
        Map<String, Long> domainCount = employees.stream()
                .map(employee -> employee.getEmail().split("@")[1]) // Extract the domain part of the email
                .collect(Collectors.groupingBy(domain -> domain, Collectors.counting()));

        /*
         * 9.) Given a string, find the words with maximum number of vowels.
         * */

        str = "the quick brown fox jumps right over the little lazy dog little";

        Map<String, Long> stringLong = Arrays.stream(str.split(" "))
                .filter(obj -> obj.replaceAll("[^aeiouAEIOU]", "").length() == 2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(stringLong);

        System.out.println();
        Arrays.stream(str.split(" "))
                .collect(Collectors.toMap(word->word, word->word.replaceAll("[^aeiouAEIOU]","").length(),(e,r)->r))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println();

        // or

        Arrays.stream(str.split(" "))
                .collect(Collectors.toMap(word->word, word->word.replaceAll("[^aeiouAEIOU]","").length(),(e,r)->r))
                .keySet().stream()
                .findFirst()
                .ifPresent(System.out::println);

        /*
         * 10.) Create a map with the word and its vowel count
         * */

        // Define vowels for checking
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        Map<String, Integer> wordVowelCountMap = Arrays.stream(str.split(" "))
                .collect(Collectors.toMap(
                        word -> word,  // The key is the original word
                        word -> (int) word.chars()  // The value is the vowel count
                                .mapToObj(c -> (char) c)
                                .filter(c -> vowels.contains(Character.toLowerCase(c)))
                                .count(),
                        (existing, replacement) -> existing // Handle duplicates, keep the original optional to use
                ));

        // Print the map (word → vowel count)
        wordVowelCountMap.forEach((word, count) -> System.out.println(word + " -> vowels: " + count));
        System.out.println();

        /*
         *  11.) Find the words with the maximum number of vowels
         * */

        str = "the quick brown fox jumps right over the little lazy dog little";
        List<String> maxVowelWords = Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(
                        word -> word.chars()
                                .mapToObj(c -> (char) c)
                                .filter(c -> vowels.contains(Character.toLowerCase(c)))
                                .count(),
                        Collectors.toList()  // Collect words with the same vowel count
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())  // Find the entry with the maximum vowel count
                .map(Map.Entry::getValue)         // Get the list of words
                .orElse(Collections.emptyList()); // Return an empty list if none found

        System.out.println(maxVowelWords);//[quick, over, little, little] all has 2 vowels

        /*
        * 11.) Given a list of integers, divide into two lists one having even numbers and other having odd numbers.
        * */

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,12);

        List<List<Integer>> answer = list.stream().collect(Collectors.groupingBy(e->e%2==0,Collectors.toList()))
                .entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        System.out.println(answer); //[[1, 3, 5, 7, 9], [2, 4, 6, 8, 10, 12]]

        /*{
            false=[1, 3, 5, 7, 9],  // All odd numbers
            true=[2, 4, 6, 8, 10, 12]  // All even numbers
        }*/


    /*
    * 12.) Given an array of integers (2,34,54,23,33,20,59,11,19,37) group the numbers by range they belong to.
    * The put should be {0=[2], 50=[54,59], 20=[23,20], 10=[11,19], 30=[34,33,37]}
    * */

    int [] arr = {2,34,54,23,33,20,59,11,19,37};
        Map<Integer, List<Integer>> collect = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(i -> i / 10 * 10));
        System.out.println(collect);

     /*
     * 13.) Given a list of String ["as,"123","32","2as"], create another integer list that contains only integers.
     * The output should be List<Integer> list =[123,32]
     * */

        List<String> ls =List.of("as","123","32","2as");
        List<String> solution = ls.stream().filter(e->e.matches("^\\d+$")).collect(Collectors.toList());
        System.out.println(solution);
        //or
        solution= ls.stream().filter(e->e.matches("[0-9]*")).collect(Collectors.toList());
        System.out.println(solution);

        /*
        * 14.) Given an array of integers arr = {5,6,7,8,5,5,8,8,7} find the sum of the unique elements.
        * */
        List<Integer> ll = List.of(5,6,7,8,5,5,8,8,7);
        ll.stream().distinct().mapToInt(i->i).sum();

        int [] a = {5,6,7,8,5,5,8,8,7};
        Arrays.stream(a).distinct().sum();

        /*
        * 15.) Given a numeric array, re-arrange the elements to form the smallest possible value
        * */

        int [] nums = {1,34,3,98,9,76,45,4,30};
        Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((a1,b1)->(a1+b1).compareTo(b1+a1))
                .forEach(System.out::print);//13033444576989
        /*
        * The comparator (a + b).compareTo(b + a) ensures that numbers are sorted such that their concatenation forms the smallest value.
        *  For example, between "3" and "30":
        * "330" (a + b) vs. "303" (b + a) — "303" is smaller, so "30" should come before "3".
        *  330-303 +ve so sort it
        * */


        /*
         * 16.) Given a numeric array, re-arrange the elements to form the highest possible value
         * */
          nums = new int[]{1,34,3,98,9,76,45,4,30,11};
         Arrays.stream(nums).mapToObj(String::valueOf).sorted((a1, b1) -> (b1 + a1).compareTo(a1 + b1)).forEach(System.out::println);

        /*
        The comparator (b + a).compareTo(a + b) ensures that the numbers are sorted in a way that their concatenation forms the largest possible value.
        For example, between "3" and "30":
        "303" (b + a) vs. "330" (b + a) — "330" is larger, so "3" should come before "30".
        303-330 -ve no sorting,  3 should be before
        */


        /*
        * 17.) Find the first non-repeated character
        * */

        str = "the quick brown fox jumps right over the little lazy dog little";
        Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(i->i.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(System.out::println);
        //or
        String finalStr = str;
        Arrays.stream(str.split(" ")).filter(c-> finalStr.indexOf(c)== finalStr.lastIndexOf(c)).findFirst().get();
    }
}
