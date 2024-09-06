package com.ncm.library.controller;

import com.ncm.library.dto.Message;
import com.ncm.library.entity.Author;
import com.ncm.library.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author();
        Author author2 = new Author();
        when(authorService.findAll()).thenReturn(Arrays.asList(author1, author2));

        ResponseEntity<Iterable<Author>> response = authorController.getAllAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, ((Iterable<Author>) response.getBody()).spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testCount() {
        when(authorService.count()).thenReturn(5L);

        ResponseEntity<Long> response = authorController.count();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5L, response.getBody());
    }

    @Test
    public void testGetOne() {
        Author author = new Author();
        when(authorService.existsById(anyInt())).thenReturn(true);
        when(authorService.findById(anyInt())).thenReturn(Optional.of(author));

        ResponseEntity<Author> response = authorController.getOne(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(author, response.getBody());
    }

    @Test
    public void testGetOne_NotFound() {
        when(authorService.existsById(anyInt())).thenReturn(false);

        ResponseEntity<Author> response = authorController.getOne(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("no existe ese autor", ((Message) ((ResponseEntity<?>) response).getBody()).getMessage());
    }

    @Test
    public void testCreate() {
        Author author = new Author();
        author.setName("New Author");
        when(authorService.existsByName(anyString())).thenReturn(false);

        ResponseEntity<?> response = authorController.create(author);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("autor guardado", ((Message) response.getBody()).getMessage());
        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    public void testCreate_InvalidName() {
        Author author = new Author();
        author.setName("");

        ResponseEntity<?> response = authorController.create(author);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("el nombre es obligatorio", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testCreate_DuplicateName() {
        Author author = new Author();
        author.setName("Existing Author");
        when(authorService.existsByName(anyString())).thenReturn(true);

        ResponseEntity<?> response = authorController.create(author);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ese nombre ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testUpdate() {
        Author existingAuthor = new Author();
        existingAuthor.setName("Existing Author");
        when(authorService.existsById(anyInt())).thenReturn(true);
        when(authorService.findById(anyInt())).thenReturn(Optional.of(existingAuthor));
        when(authorService.existsByName(anyString())).thenReturn(false);

        Author updatedAuthor = new Author();
        updatedAuthor.setName("Updated Author");

        ResponseEntity<?> response = authorController.update(1, updatedAuthor);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("autor actualizado", ((Message) response.getBody()).getMessage());
        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    public void testUpdate_NotFound() {
        when(authorService.existsById(anyInt())).thenReturn(false);

        Author updatedAuthor = new Author();
        updatedAuthor.setName("Updated Author");

        ResponseEntity<?> response = authorController.update(1, updatedAuthor);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("no existe ese autor", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testUpdate_InvalidName() {
        when(authorService.existsById(anyInt())).thenReturn(true);

        Author updatedAuthor = new Author();
        updatedAuthor.setName("");

        ResponseEntity<?> response = authorController.update(1, updatedAuthor);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("el nombre es obligatorio", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testUpdate_DuplicateName() {
        Author existingAuthor = new Author();
        existingAuthor.setName("Existing Author");
        when(authorService.existsById(anyInt())).thenReturn(true);
        when(authorService.findById(anyInt())).thenReturn(Optional.of(existingAuthor));
        when(authorService.existsByName(anyString())).thenReturn(true);
        when(authorService.findByName(anyString())).thenReturn(Optional.of(existingAuthor));

        Author updatedAuthor = new Author();
        updatedAuthor.setName("Existing Author");

        ResponseEntity<?> response = authorController.update(1, updatedAuthor);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ese nombre ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testDelete() {
        when(authorService.existsById(anyInt())).thenReturn(true);

        ResponseEntity<?> response = authorController.delete(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("autor eliminado", ((Message) response.getBody()).getMessage());
        verify(authorService, times(1)).deleteById(anyInt());
    }

    @Test
    public void testDelete_NotFound() {
        when(authorService.existsById(anyInt())).thenReturn(false);

        ResponseEntity<?> response = authorController.delete(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("no existe ese autor", ((Message) response.getBody()).getMessage());
    }
}