package com.ncm.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncm.library.entity.Book;
import com.ncm.library.repository.BookRepository;

@Service
@Transactional
public class BookService {
  
  @Autowired
  private BookRepository bookRepository;
  
  public Iterable<Book> findAll() {
    Iterable<Book> list = bookRepository.findAll();
    return list;
  }

  public long count() {
    long count = bookRepository.count();
    return count;
  }

  public Optional<Book> findById(Integer IDbook) {
    return bookRepository.findById(IDbook);
  }

  public Optional<Book> findByTitle(String np) {
    return bookRepository.findByTitle(np);
  }

  public void save(Book book) {
    bookRepository.save(book);
  }

  public void deleteById(Integer IDbook) {
    bookRepository.deleteById(IDbook);
  }

  public boolean existsById(Integer IDbook) {
    return bookRepository.existsById(IDbook);
  }

  public boolean existsByTitle(String np) {
    return bookRepository.existsByTitle(np);
  }

}
