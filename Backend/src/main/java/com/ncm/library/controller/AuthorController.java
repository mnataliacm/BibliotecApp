package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ncm.library.dto.Message;
import com.ncm.library.entity.Author;
import com.ncm.library.service.AuthorService;

@RestController
@RequestMapping("/api/library/author")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Author>> getAllAuthors() {
        Iterable<Author> authors = authorService.findAll();
        return new ResponseEntity<Iterable<Author>>(authors, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        long count = authorService.count();
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }

    @GetMapping("/detail/{idauthor}")
    public ResponseEntity<Author> getOne(@PathVariable Integer idauthor) {
        if (!authorService.existsById(idauthor))
            return new ResponseEntity(new Message("no existe ese autor"), HttpStatus.NOT_FOUND);
        Author author = authorService.findById(idauthor).get();
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Author author) {
        if (StringUtils.isBlank(author.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (authorService.existsByName(author.getName()))
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        authorService.save(author);
        return new ResponseEntity(new Message("autor guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/modify/{idauthor}")
    public ResponseEntity<?> update(@PathVariable Integer idauthor, @RequestBody Author author) {
        if (!authorService.existsById(idauthor))
            return new ResponseEntity(new Message("no existe ese autor"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(author.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (authorService.existsByName(author.getName()) && authorService.findByName(author.getName()).get().getidauthor() != idauthor)
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Author authorUpdate = authorService.findById(idauthor).get();
        authorUpdate.setName(author.getName());
        authorService.save(authorUpdate);
        return new ResponseEntity(new Message("autor actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idauthor}")
    public ResponseEntity<?> delete(@PathVariable Integer idauthor) {
        if (!authorService.existsById(idauthor))
            return new ResponseEntity(new Message("no existe ese autor"), HttpStatus.NOT_FOUND);
        authorService.deleteById(idauthor);
        return new ResponseEntity(new Message("autor eliminado"), HttpStatus.OK);
    }

}