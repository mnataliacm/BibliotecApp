package com.ncm.library.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ncm.library.entity.Editorial;
import com.ncm.library.repository.EditorialRepository;

public class EditorialServiceTest {

  @Mock
  private EditorialRepository editorialRepository;

  @InjectMocks
  private EditorialService editorialService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindAll() {
    Iterable<Editorial> editorials = Arrays.asList(new Editorial(), new Editorial());
    when(editorialRepository.findAll()).thenReturn(editorials);

    Iterable<Editorial> result = editorialService.findAll();
    assertEquals(editorials, result);
    verify(editorialRepository).findAll();
  }

  @Test
  public void testCount() {
    long count = 5L;
    when(editorialRepository.count()).thenReturn(count);

    long result = editorialService.count();
    assertEquals(count, result);
    verify(editorialRepository).count();
  }

  @Test
  public void testFindById() {
    Optional<Editorial> editorial = Optional.of(new Editorial());
    when(editorialRepository.findById(1)).thenReturn(editorial);

    Optional<Editorial> result = editorialService.findById(1);
    assertEquals(editorial, result);
    verify(editorialRepository).findById(1);
  }

  @Test
  public void testFindByName() {
    Optional<Editorial> editorial = Optional.of(new Editorial());
    when(editorialRepository.findByName("Test Name")).thenReturn(editorial);

    Optional<Editorial> result = editorialService.findByName("Test Name");
    assertEquals(editorial, result);
    verify(editorialRepository).findByName("Test Name");
  }

  @Test
  public void testExistsById() {
    when(editorialRepository.existsById(1)).thenReturn(true);

    boolean result = editorialService.existsById(1);
    assertTrue(result);
    verify(editorialRepository).existsById(1);
  }

  @Test
  public void testExistsByName() {
    when(editorialRepository.existsByName("Test Name")).thenReturn(true);

    boolean result = editorialService.existsByName("Test Name");
    assertTrue(result);
    verify(editorialRepository).existsByName("Test Name");
  }

  @Test
  public void testSave() {
    Editorial editorial = new Editorial();
    editorialService.save(editorial);
    verify(editorialRepository).save(editorial);
  }

  @Test
  public void testDeleteById() {
    editorialService.deleteById(1);
    verify(editorialRepository).deleteById(1);
  }
}
