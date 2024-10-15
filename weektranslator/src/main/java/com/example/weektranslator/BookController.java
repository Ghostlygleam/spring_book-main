package com.example.weektranslator;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private List<Genre> genres = Arrays.asList(
            new Genre(1L, "Fantasy"),
            new Genre(2L, "Adventure"),
            new Genre(3L, "Sci-Fi")
    );

    private List<Author> authors = Arrays.asList(
            new Author(1L, "Brandon Sanderson"),
            new Author(2L, "J.R.R. Tolkien"),
            new Author(3L, "Frank Herbert")
    );

    private List<Book> books = Arrays.asList(
            new Book(1L, "Mistborn", authors.get(0), genres.get(0)),
            new Book(2L, "The Hobbit", authors.get(1), genres.get(0)),
            new Book(3L, "Dune", authors.get(2), genres.get(2))
    );

    @GetMapping("/author/{name}")
    public AuthorResponse getBooksByAuthor(@PathVariable String name) {
        Optional<Author> author = authors.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("Автор с именем " + name + " не найден");
        }

        List<Book> booksByAuthor = books.stream()
                .filter(b -> b.getAuthor().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        return new AuthorResponse(author.get().getName(), booksByAuthor);
    }

    public static class AuthorResponse {
        private String author;
        private List<Book> books;

        public AuthorResponse(String author, List<Book> books) {
            this.author = author;
            this.books = books;
        }

        // Getters and setters

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }
    }
}
