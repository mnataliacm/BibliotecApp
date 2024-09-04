package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ncm.library.dto.Message;
import com.ncm.library.entity.Book;
import com.ncm.library.service.BookService;

@RestController
@RequestMapping("/api/library/book")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BookController {
  
  @Autowired
  private BookService bookService;

  @GetMapping("/all")
  public ResponseEntity<Iterable<Book>> getAllBooks() {
    Iterable<Book> books = bookService.findAll();
    return new ResponseEntity<Iterable<Book>>(books, HttpStatus.OK);
  }

  @GetMapping("/count")
  public ResponseEntity<Long> count() {
    long count = bookService.count();
    return new ResponseEntity<Long>(count, HttpStatus.OK);
  }

  @GetMapping("/detail/{IDbook}")
  public ResponseEntity<Book> getOne(@PathVariable Integer IDbook) {
    if (!bookService.existsById(IDbook))
      return new ResponseEntity(new Message("no existe ese libro"), HttpStatus.NOT_FOUND);
    Book book = bookService.findById(IDbook).get();
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<?> create(@RequestBody Book book) {
    if (StringUtils.isBlank(book.getTitle()))
      return new ResponseEntity(new Message("el título es obligatorio"), HttpStatus.BAD_REQUEST);
    if (bookService.existsByTitle(book.getTitle()))
      return new ResponseEntity(new Message("ese título ya existe"), HttpStatus.BAD_REQUEST);
    bookService.save(book);
    return new ResponseEntity(new Message("libro guardado"), HttpStatus.CREATED);
  }

  @PostMapping("/modify/{IDbook}")
  public ResponseEntity<?> update(@PathVariable Integer IDbook, @RequestBody Book book) {
    if (!bookService.existsById(IDbook))
      return new ResponseEntity(new Message("no existe ese libro"), HttpStatus.NOT_FOUND);
    if (StringUtils.isBlank(book.getTitle()))
      return new ResponseEntity(new Message("el título es obligatorio"), HttpStatus.BAD_REQUEST);
    if (bookService.existsByTitle(book.getTitle()) && bookService.findByTitle(book.getTitle()).get().getISBN() != IDbook.longValue())
      return new ResponseEntity(new Message("ese título ya existe"), HttpStatus.BAD_REQUEST);
    Book bookUpdate = bookService.findById(IDbook).get();
    bookUpdate.setTitle(book.getTitle());
    bookUpdate.setYear(book.getYear());
    bookUpdate.setIDgenre(book.getIDgenre());
    bookUpdate.setidauthor(book.getidauthor());
    bookUpdate.setIDeditorial(book.getIDeditorial());
    bookService.save(bookUpdate);
    return new ResponseEntity(new Message("libro actualizado"), HttpStatus.CREATED);
  }

  @GetMapping("/delete/{IDbook}")
  public ResponseEntity<?> delete(@PathVariable Integer IDbook) {
    if (!bookService.existsById(IDbook))
      return new ResponseEntity(new Message("no existe ese libro"), HttpStatus.NOT_FOUND);
    bookService.deleteById(IDbook);
    return new ResponseEntity(new Message("libro eliminado"), HttpStatus.OK);
  }

}
