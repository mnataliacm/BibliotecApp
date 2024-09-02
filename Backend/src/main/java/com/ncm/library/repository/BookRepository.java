package com.ncm.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository;

import com.ncm.library.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    public Optional<Book> findByTitle(String np);
    boolean existsByTitle(String np);

}
