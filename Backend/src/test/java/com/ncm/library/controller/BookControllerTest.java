package com.ncm.library.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ncm.library.dto.Message;
import com.ncm.library.entity.Book;
import com.ncm.library.service.BookService;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        when(bookService.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));
        ResponseEntity<Iterable<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((Iterable<Book>) response.getBody()).spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testCount() {
        when(bookService.count()).thenReturn(5L);
        ResponseEntity<Long> response = bookController.count();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5L, response.getBody());
    }

    @Test
    public void testGetOne() {
        Book book = new Book();
        when(bookService.existsById(1)).thenReturn(true);
        when(bookService.findById(1)).thenReturn(Optional.of(book));
        ResponseEntity<Book> response = bookController.getOne(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());

        when(bookService.existsById(2)).thenReturn(false);
        response = bookController.getOne(2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe ese libro", ((Message) ((ResponseEntity<?>) response).getBody()).getMessage());
    }

    @Test
    public void testCreate() {
        Book book = new Book();
        book.setTitle("New Book");

        when(bookService.existsByTitle("New Book")).thenReturn(false);
        ResponseEntity<?> response = bookController.create(book);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("libro guardado", ((Message) response.getBody()).getMessage());

        book.setTitle("");
        response = bookController.create(book);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el título es obligatorio", ((Message) response.getBody()).getMessage());

        book.setTitle("Existing Book");
        when(bookService.existsByTitle("Existing Book")).thenReturn(true);
        response = bookController.create(book);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ese título ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testUpdate() {
        Book book = new Book();
        book.setTitle("Updated Book");

        when(bookService.existsById(1)).thenReturn(true);
        when(bookService.findById(1)).thenReturn(Optional.of(book));
        when(bookService.existsByTitle("Updated Book")).thenReturn(false);
        ResponseEntity<?> response = bookController.update(1, book);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("libro actualizado", ((Message) response.getBody()).getMessage());

        when(bookService.existsById(2)).thenReturn(false);
        response = bookController.update(2, book);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe ese libro", ((Message) response.getBody()).getMessage());

        book.setTitle("");
        response = bookController.update(1, book);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el título es obligatorio", ((Message) response.getBody()).getMessage());

        book.setTitle("Existing Book");
        when(bookService.existsByTitle("Existing Book")).thenReturn(true);
        when(bookService.findByTitle("Existing Book")).thenReturn(Optional.of(book));
        response = bookController.update(1, book);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ese título ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testDelete() {
        when(bookService.existsById(1)).thenReturn(true);
        ResponseEntity<?> response = bookController.delete(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("libro eliminado", ((Message) response.getBody()).getMessage());

        when(bookService.existsById(2)).thenReturn(false);
        response = bookController.delete(2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe ese libro", ((Message) response.getBody()).getMessage());
    }
}
