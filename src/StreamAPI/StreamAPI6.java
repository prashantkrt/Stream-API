package StreamAPI;
import java.util.List;
import java.util.stream.Collectors;

class Cover {
    private double height;
    private double width;

    public Cover(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea() {
        return this.height * this.width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}

class Book {
    private String title;
    private List<Cover> covers;

    public Book(String title, List<Cover> covers) {
        this.title = title;
        this.covers = covers;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public String getTitle() {
        return title;
    }
}



public class StreamAPI6 {
    public static List<Book> filterBooksByCoverArea(List<Book> books, double areaThreshold) {
        return books.stream()
                .filter(book -> book.getCovers().stream()
                        .flatMap(cover -> List.of(cover).stream()) // Stream of covers (stream within stream)
                        .anyMatch(cover -> cover.getArea() > areaThreshold))
                .collect(Collectors.toList());

// Both are correct
//        return books.stream()
//                .filter(book -> book.getCovers().stream()
//                        .anyMatch(cover -> cover.getArea() > areaThreshold))
//                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Example usage
        Cover cover1 = new Cover(10, 5);  // Area = 50
        Cover cover2 = new Cover(15, 3);  // Area = 45
        Book book1 = new Book("Book One", List.of(cover1, cover2));

        Cover cover3 = new Cover(7, 8);   // Area = 56
        Book book2 = new Book("Book Two", List.of(cover3));

        List<Book> books = List.of(book1, book2);

        double areaThreshold = 50;

        //In the example, the output would be the titles of the books that have at least one cover with an area greater than 50.
        // filter books which has the cover which has the area more than the threshold.
        List<Book> filteredBooks = filterBooksByCoverArea(books, areaThreshold);

        filteredBooks.forEach(book -> System.out.println(book.getTitle()));
    }
}
