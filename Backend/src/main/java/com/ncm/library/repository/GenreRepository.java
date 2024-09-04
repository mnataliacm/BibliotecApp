package com.ncm.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ncm.library.entity.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    
  public Optional<Genre> findByName(String name);
  boolean existsByName(String name);

}