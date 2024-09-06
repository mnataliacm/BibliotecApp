package com.ncm.library.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ncm.library.entity.Genre;
import com.ncm.library.repository.GenreRepository;

public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        genreService.findAll();
        verify(genreRepository).findAll();
    }

    @Test
    public void testCount() {
        genreService.count();
        verify(genreRepository).count();
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        genreService.findById(id);
        verify(genreRepository).findById(id);
    }

    @Test
    public void testFindByName() {
        String name = "Fiction";
        genreService.findByName(name);
        verify(genreRepository).findByName(name);
    }

    @Test
    public void testExistsById() {
        Integer id = 1;
        genreService.existsById(id);
        verify(genreRepository).existsById(id);
    }

    @Test
    public void testExistsByName() {
        String name = "Fiction";
        genreService.existsByName(name);
        verify(genreRepository).existsByName(name);
    }

    @Test
    public void testSave() {
        Genre genre = new Genre();
        genreService.save(genre);
        verify(genreRepository).save(genre);
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        genreService.deleteById(id);
        verify(genreRepository).deleteById(id);
    }
}
