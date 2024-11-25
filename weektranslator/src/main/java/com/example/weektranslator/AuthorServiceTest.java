package com.example.weektranslator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    private final AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
    private final AuthorService authorService = new AuthorService(authorRepository);

    @Test
    void testGetAuthorById() {
        Long authorId = 1L;
        Author mockAuthor = new Author(authorId, "J.K. Rowling");
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(mockAuthor));

        Author result = authorService.getAuthorById(authorId);

        assertNotNull(result);
        assertEquals(mockAuthor.getId(), result.getId());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    void testSaveAuthor() {
        Author newAuthor = new Author(null, "George Orwell");
        Author savedAuthor = new Author(1L, "George Orwell");
        when(authorRepository.save(newAuthor)).thenReturn(savedAuthor);

        Author result = authorService.saveAuthor(newAuthor);

        assertNotNull(result);
        assertEquals(savedAuthor.getId(), result.getId());
        verify(authorRepository, times(1)).save(newAuthor);
    }
}

