package com.ncm.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ncm.library.entity.Author;
import com.ncm.library.repository.AuthorRepository;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Author author1 = new Author();
        Author author2 = new Author();
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        Iterable<Author> authors = authorService.findAll();
        assertNotNull(authors);
        assertEquals(2, ((Collection<?>) authors).size());
    }

    @Test
    public void testCount() {
        when(authorRepository.count()).thenReturn(2L);

        long count = authorService.count();
        assertEquals(2L, count);
    }

    @Test
    public void testFindById() {
        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Optional<Author> foundAuthor = authorService.findById(1);
        assertTrue(foundAuthor.isPresent());
        assertEquals(author, foundAuthor.get());
    }

    @Test
    public void testFindByName() {
        Author author = new Author();
        when(authorRepository.findByName("John Doe")).thenReturn(Optional.of(author));

        Optional<Author> foundAuthor = authorService.findByName("John Doe");
        assertTrue(foundAuthor.isPresent());
        assertEquals(author, foundAuthor.get());
    }

    @Test
    public void testExistsById() {
        when(authorRepository.existsById(1)).thenReturn(true);

        boolean exists = authorService.existsById(1);
        assertTrue(exists);
    }

    @Test
    public void testExistsByName() {
        when(authorRepository.existsByName("John Doe")).thenReturn(true);

        boolean exists = authorService.existsByName("John Doe");
        assertTrue(exists);
    }

    @Test
    public void testSave() {
        Author author = new Author();
        authorService.save(author);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testDeleteById() {
        authorService.deleteById(1);
        verify(authorRepository, times(1)).deleteById(1);
    }
}