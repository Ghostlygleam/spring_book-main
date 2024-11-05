package com.example.weektranslator;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
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

    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(1L, "Mistborn", authors.get(0), genres.get(0)),
            new Book(2L, "The Hobbit", authors.get(1), genres.get(0)),
            new Book(3L, "Dune", authors.get(2), genres.get(2))
    ));

    // 1. Создание новой книги
    @PostMapping("/book")
    public Book createBook(@RequestBody Book newBook) {
        books.add(newBook);
        return newBook;
    }

    // 2. Изменение информации о книге
    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> bookOptional = books.stream().filter(book -> book.getId().equals(id)).findFirst();

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("Книга с id " + id + " не найдена");
        }

        Book existingBook = bookOptional.get();
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());

        return existingBook;
    }

    // 3. Удаление книги
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));

        if (!removed) {
            throw new BookNotFoundException("Книга с id " + id + " не найдена");
        }

        return "Книга с id " + id + " успешно удалена";
    }

    // Исключение для ненайденной книги
    @ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Книга не найдена")
    static class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }
}
