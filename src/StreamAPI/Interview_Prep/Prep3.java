package StreamAPI.Interview_Prep;
import java.util.List;
import java.util.stream.Collectors;

class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Book {
    private String title;
    private List<Author> authors;

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }
}

class Library {
    private String name;
    private List<Book> books;

    public Library(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
public class Prep3 {
    public static List<Library> filterLibrariesByAuthorName(List<Library> libraries, String authorName) {
        return libraries.stream()
                .filter(library -> library.getBooks().stream()
                        .flatMap(book -> book.getAuthors().stream()) // Stream of authors
                        .anyMatch(author -> author.getName().equalsIgnoreCase(authorName)))
                .collect(Collectors.toList());

//        return libraries.stream()
//                .filter(library -> library.getBooks().stream()
//                        .anyMatch(book -> book.getAuthors().stream()
//                                .anyMatch(author -> author.getName().equalsIgnoreCase(authorName)))
//                )
//                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Example usage
        Author author1 = new Author("John");
        Author author2 = new Author("Emily");
        Author author3 = new Author("Jane");

        Book book1 = new Book("Book One", List.of(author1, author2));
        Book book2 = new Book("Book Two", List.of(author3));

        Library library1 = new Library("Library One", List.of(book1));
        Library library2 = new Library("Library Two", List.of(book2));

        List<Library> libraries = List.of(library1, library2);

        String searchAuthorName = "John";


        //The final result is a list of libraries that contain books written by the specified author.
        List<Library> filteredLibraries = filterLibrariesByAuthorName(libraries, searchAuthorName);

        filteredLibraries.forEach(library -> System.out.println(library.getName()));
    }
}
