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

  public Optional<Author> findById(Integer idauthor) {
    return authorRepository.findById(idauthor);
  }

  public Optional<Author> findByName(String np) {
    return authorRepository.findByName(np);
  }

  public boolean existsById(Integer idauthor) {
    return authorRepository.existsById(idauthor);
  } 

  public boolean existsByName(String np) {
    return authorRepository.existsByName(np);
  } 

    public void save(Author author) {
    authorRepository.save(author);
  }

  public void deleteById(Integer idauthor) {
    authorRepository.deleteById(idauthor);
  }
  
}