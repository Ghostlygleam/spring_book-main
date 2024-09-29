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

    private List<Book> books = Arrays.asList(
            new Book(1L, "Mistborn", "Brandon Sanderson", genres.get(0)),
            new Book(2L, "The Hobbit", "J.R.R. Tolkien", genres.get(0)),
            new Book(3L, "Dune", "Frank Herbert", genres.get(2))
    );

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException("Книга с id " + id + " не найдена");
        }
    }

    @GetMapping("/genre/{id}")
    public GenreResponse getBooksByGenre(@PathVariable Long id) {
        Optional<Genre> genre = genres.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst();

        if (!genre.isPresent()) {
            throw new GenreNotFoundException("Жанр с id " + id + " не найден");
        }

        List<Book> booksInGenre = books.stream()
                .filter(b -> b.getGenre().getId().equals(id))
                .collect(Collectors.toList());

        return new GenreResponse(genre.get().getName(), booksInGenre);
    }

    // Создадим отдельный класс для ответа JSON
    public static class GenreResponse {
        private String genre;
        private List<Book> books;

        public GenreResponse(String genre, List<Book> books) {
            this.genre = genre;
            this.books = books;
        }

        // Getters and setters

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }
    }
}
