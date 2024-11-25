package com.example.weektranslator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final BookService bookService = new BookService(bookRepository);

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book mockBook = new Book(bookId, "1984", "George Orwell");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        Book result = bookService.getBookById(bookId);

        assertNotNull(result);
        assertEquals(mockBook.getId(), result.getId());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}