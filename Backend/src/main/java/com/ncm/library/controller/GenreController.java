package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import com.ncm.library.dto.Message;
import com.ncm.library.entity.Genre;
import com.ncm.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/genre")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Genre>> getAllGenres() {
        Iterable<Genre> genres = genreService.findAll();
        return new ResponseEntity<Iterable<Genre>>(genres, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        long count = genreService.count();
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }

    @GetMapping("/detail/{IDgenre}")
    public ResponseEntity<Genre> getOne(@PathVariable Integer IDgenre) {
        if (!genreService.existsById(IDgenre))
            return new ResponseEntity(new Message("no existe ese genero"), HttpStatus.NOT_FOUND);
        Genre genre = genreService.findById(IDgenre).get();
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Genre genre) {
        if (StringUtils.isBlank(genre.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (genreService.existsByName(genre.getName()))
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        genreService.save(genre);
        return new ResponseEntity(new Message("genero guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/modify/{IDgenre}")
    public ResponseEntity<?> update(@PathVariable Integer IDgenre, @RequestBody Genre genre) {
        if (!genreService.existsById(IDgenre))
            return new ResponseEntity(new Message("no existe ese genero"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(genre.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (genreService.existsByName(genre.getName()) && genreService.findByName(genre.getName()).get().getIDgenre() != IDgenre)
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Genre genreUpdate = genreService.findById(IDgenre).get();
        genreUpdate.setName(genre.getName());
        genreService.save(genreUpdate);
        return new ResponseEntity(new Message("genero actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{IDgenre}")
    public ResponseEntity<?> delete(@PathVariable Integer IDgenre) {
        if (!genreService.existsById(IDgenre))
            return new ResponseEntity(new Message("no existe ese genero"), HttpStatus.NOT_FOUND);
        genreService.deleteById(IDgenre);
        return new ResponseEntity(new Message("genero eliminado"), HttpStatus.OK);
    }

}