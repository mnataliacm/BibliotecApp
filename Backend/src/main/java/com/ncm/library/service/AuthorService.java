package com.ncm.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncm.library.entity.Author;
import com.ncm.library.repository.AuthorRepository;

@Service
@Transactional
public class AuthorService {
  
  @Autowired
  private AuthorRepository authorRepository;
  
  public Iterable<Author> findAll() {
    Iterable<Author> list = authorRepository.findAll();
    return list;
  }

  public long count() {
    long count = authorRepository.count();
    return count;
  }

  public Optional<Author> findById(Integer IDauthor) {
    return authorRepository.findById(IDauthor);
  }

  public Optional<Author> findByName(String np) {
    return authorRepository.findByName(np);
  }

  public void save(Author author) {
    authorRepository.save(author);
  }

  public void deleteById(Integer IDauthor) {
    authorRepository.deleteById(IDauthor);
  }

  public boolean existsById(Integer IDauthor) {
    return authorRepository.existsById(IDauthor);
  } 

  public boolean existsByName(String np) {
    return authorRepository.existsByName(np);
  } 
  
}