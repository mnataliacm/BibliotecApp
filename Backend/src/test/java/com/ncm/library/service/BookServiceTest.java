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

import com.ncm.library.entity.Book;
import com.ncm.library.repository.BookRepository;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Book book1 = new Book();
        Book book2 = new Book();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        Iterable<Book> books = bookService.findAll();
        assertNotNull(books);
        assertEquals(2, ((Collection<?>) books).size());
    }

    @Test
    public void testCount() {
        when(bookRepository.count()).thenReturn(2L);

        long count = bookService.count();
        assertEquals(2L, count);
    }

    @Test
    public void testFindById() {
        Book book = new Book();
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.findById(1);
        assertTrue(foundBook.isPresent());
        assertEquals(book, foundBook.get());
    }

    @Test
    public void testFindByTitle() {
        Book book = new Book();
        when(bookRepository.findByTitle("Test Title")).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.findByTitle("Test Title");
        assertTrue(foundBook.isPresent());
        assertEquals(book, foundBook.get());
    }

    @Test
    public void testSave() {
        Book book = new Book();
        bookService.save(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testDeleteById() {
        bookService.deleteById(1);
        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    public void testExistsById() {
        when(bookRepository.existsById(1)).thenReturn(true);

        boolean exists = bookService.existsById(1);
        assertTrue(exists);
    }

    @Test
    public void testExistsByTitle() {
        when(bookRepository.existsByTitle("Test Title")).thenReturn(true);

        boolean exists = bookService.existsByTitle("Test Title");
        assertTrue(exists);
    }
}
