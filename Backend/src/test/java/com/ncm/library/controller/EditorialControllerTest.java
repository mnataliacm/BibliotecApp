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
import com.ncm.library.entity.Editorial;
import com.ncm.library.service.EditorialService;

public class EditorialControllerTest {

    @Mock
    private EditorialService editorialService;

    @InjectMocks
    private EditorialController editorialController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEditorials() {
        when(editorialService.findAll()).thenReturn(Arrays.asList(new Editorial(), new Editorial()));
        ResponseEntity<Iterable<Editorial>> response = editorialController.getAllEditorials();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((Iterable<Editorial>) response.getBody()).spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testCount() {
        when(editorialService.count()).thenReturn(5L);
        ResponseEntity<Long> response = editorialController.count();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5L, response.getBody());
    }

    @Test
    public void testGetOne() {
        Editorial editorial = new Editorial();
        when(editorialService.existsById(1)).thenReturn(true);
        when(editorialService.findById(1)).thenReturn(Optional.of(editorial));
        ResponseEntity<Editorial> response = editorialController.getOne(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(editorial, response.getBody());

        when(editorialService.existsById(2)).thenReturn(false);
        response = editorialController.getOne(2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe esa editorial", ((Message) ((ResponseEntity<?>) response).getBody()).getMessage());
    }

    @Test
    public void testCreate() {
        Editorial editorial = new Editorial();
        editorial.setName("New Editorial");

        when(editorialService.existsByName("New Editorial")).thenReturn(false);
        ResponseEntity<?> response = editorialController.create(editorial);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("editorial guardada", ((Message) response.getBody()).getMessage());

        editorial.setName("");
        response = editorialController.create(editorial);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el nombre es obligatorio", ((Message) response.getBody()).getMessage());

        editorial.setName("Existing Editorial");
        when(editorialService.existsByName("Existing Editorial")).thenReturn(true);
        response = editorialController.create(editorial);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ese nombre ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testUpdate() {
        Editorial editorial = new Editorial();
        editorial.setName("Updated Editorial");

        when(editorialService.existsById(1)).thenReturn(true);
        when(editorialService.findById(1)).thenReturn(Optional.of(editorial));
        when(editorialService.existsByName("Updated Editorial")).thenReturn(false);
        ResponseEntity<?> response = editorialController.update(1, editorial);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("editorial actualizada", ((Message) response.getBody()).getMessage());

        when(editorialService.existsById(2)).thenReturn(false);
        response = editorialController.update(2, editorial);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe esa editorial", ((Message) response.getBody()).getMessage());

        editorial.setName("");
        response = editorialController.update(1, editorial);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el nombre es obligatorio", ((Message) response.getBody()).getMessage());

        editorial.setName("Existing Editorial");
        when(editorialService.existsByName("Existing Editorial")).thenReturn(true);
        when(editorialService.findByName("Existing Editorial")).thenReturn(Optional.of(editorial));
        response = editorialController.update(1, editorial);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ese nombre ya existe", ((Message) response.getBody()).getMessage());
    }

    @Test
    public void testDelete() {
        when(editorialService.existsById(1)).thenReturn(true);
        ResponseEntity<?> response = editorialController.delete(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("editorial eliminada", ((Message) response.getBody()).getMessage());

        when(editorialService.existsById(2)).thenReturn(false);
        response = editorialController.delete(2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe esa editorial", ((Message) response.getBody()).getMessage());
    }
}
