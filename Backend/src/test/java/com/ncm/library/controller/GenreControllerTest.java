package com.ncm.library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ncm.library.entity.Genre;
import com.ncm.library.service.GenreService;
public class GenreControllerTest {

  @Mock
  private GenreService genreService;

  @InjectMocks
  private GenreController genreController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllGenres() {
    Genre genre1 = new Genre();
    Genre genre2 = new Genre();
    when(genreService.findAll()).thenReturn(Arrays.asList(genre1, genre2));

    ResponseEntity<Iterable<Genre>> response = genreController.getAllGenres();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, ((Iterable<Genre>) response.getBody()).spliterator().getExactSizeIfKnown());
    verify(genreService, times(1)).findAll();
  }

  @Test
  public void testCount() {
    when(genreService.count()).thenReturn(5L);

    ResponseEntity<Long> response = genreController.count();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(5L, response.getBody());
    verify(genreService, times(1)).count();
  }

  @Test
  public void testGetOne() {
    Genre genre = new Genre();
    when(genreService.existsById(1)).thenReturn(true);
    when(genreService.findById(1)).thenReturn(Optional.of(genre));

    ResponseEntity<Genre> response = genreController.getOne(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(genre, response.getBody());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(1)).findById(1);
  }

  @Test
  public void testGetOneNotFound() {
    when(genreService.existsById(1)).thenReturn(false);

    ResponseEntity<Genre> response = genreController.getOne(1);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(0)).findById(1);
  }

  @Test
  public void testCreate() {
    Genre genre = new Genre();
    genre.setName("Fiction");
    when(genreService.existsByName("Fiction")).thenReturn(false);

    ResponseEntity<?> response = genreController.create(genre);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(genreService, times(1)).existsByName("Fiction");
    verify(genreService, times(1)).save(genre);
  }

  @Test
  public void testCreateNameBlank() {
    Genre genre = new Genre();
    genre.setName("");

    ResponseEntity<?> response = genreController.create(genre);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(genreService, times(0)).existsByName(anyString());
    verify(genreService, times(0)).save(any(Genre.class));
  }

  @Test
  public void testCreateNameExists() {
    Genre genre = new Genre();
    genre.setName("Fiction");
    when(genreService.existsByName("Fiction")).thenReturn(true);

    ResponseEntity<?> response = genreController.create(genre);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(genreService, times(1)).existsByName("Fiction");
    verify(genreService, times(0)).save(any(Genre.class));
  }

  @Test
  public void testUpdate() {
    Genre genre = new Genre();
    genre.setName("Fiction");
    Genre existingGenre = new Genre();
    existingGenre.setIDgenre(1);
    when(genreService.existsById(1)).thenReturn(true);
    when(genreService.existsByName("Fiction")).thenReturn(false);
    when(genreService.findById(1)).thenReturn(Optional.of(existingGenre));

    ResponseEntity<?> response = genreController.update(1, genre);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(1)).existsByName("Fiction");

    verify(genreService, times(1)).findById(1);
    verify(genreService, times(1)).save(existingGenre);
  }

  @Test
  public void testUpdateNotFound() {
    Genre genre = new Genre();
    when(genreService.existsById(1)).thenReturn(false);

    ResponseEntity<?> response = genreController.update(1, genre);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(0)).existsByName(anyString());
    verify(genreService, times(0)).findById(anyInt());
    verify(genreService, times(0)).save(any(Genre.class));
  }

  @Test
  public void testUpdateNameBlank() {
    Genre genre = new Genre();
    genre.setName("");
    when(genreService.existsById(1)).thenReturn(true);

    ResponseEntity<?> response = genreController.update(1, genre);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(0)).existsByName(anyString());
    verify(genreService, times(0)).findById(anyInt());
    verify(genreService, times(0)).save(any(Genre.class));
  }

  @Test
  public void testUpdateNameExists() {
    Genre genre = new Genre();
    genre.setName("Fiction");
    Genre existingGenre = new Genre();
    existingGenre.setIDgenre(2);
    when(genreService.existsById(1)).thenReturn(true);
    when(genreService.existsByName("Fiction")).thenReturn(true);
    when(genreService.findByName("Fiction")).thenReturn(Optional.of(existingGenre));

    ResponseEntity<?> response = genreController.update(1, genre);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(1)).existsByName("Fiction");
    verify(genreService, times(1)).findByName("Fiction");
    verify(genreService, times(0)).findById(anyInt());
    verify(genreService, times(0)).save(any(Genre.class));
  }

  @Test
  public void testDelete() {
    when(genreService.existsById(1)).thenReturn(true);

    ResponseEntity<?> response = genreController.delete(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(1)).deleteById(1);
  }

  @Test
  public void testDeleteNotFound() {
    when(genreService.existsById(1)).thenReturn(false);

    ResponseEntity<?> response = genreController.delete(1);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(genreService, times(1)).existsById(1);
    verify(genreService, times(0)).deleteById(anyInt());
  }
}