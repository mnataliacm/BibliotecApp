package com.ncm.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncm.library.entity.Genre;
import com.ncm.library.repository.GenreRepository;

@Service
@Transactional
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }

    public long count() {
        return genreRepository.count();
    }

    public Optional<Genre> findById(Integer IDgenre) {
        return genreRepository.findById(IDgenre);
    }

    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public boolean existsById(Integer IDgenre) {
        return genreRepository.existsById(IDgenre);
    }

    public boolean existsByName(String name) {
        return genreRepository.existsByName(name);
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteById(Integer IDgenre) {
        genreRepository.deleteById(IDgenre);
    }
    
}