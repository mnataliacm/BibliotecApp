package com.ncm.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository;

import com.ncm.library.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    
  public Optional<Author> findByName(String id);
  boolean existsByName(String id);

}