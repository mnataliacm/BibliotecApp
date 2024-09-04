package com.ncm.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ncm.library.entity.Editorial;

@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Integer> {
    
  public Optional<Editorial> findByName(String name);
  boolean existsByName(String name);

}