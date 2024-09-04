package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import com.ncm.library.dto.Message;
import com.ncm.library.entity.Editorial;
import com.ncm.library.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/editorial")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Editorial>> getAllEditorials() {
        Iterable<Editorial> editorials = editorialService.findAll();
        return new ResponseEntity<Iterable<Editorial>>(editorials, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        long count = editorialService.count();
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }

    @GetMapping("/detail/{IDeditorial}")
    public ResponseEntity<Editorial> getOne(@PathVariable Integer IDeditorial) {
        if (!editorialService.existsById(IDeditorial))
            return new ResponseEntity(new Message("no existe esa editorial"), HttpStatus.NOT_FOUND);
        Editorial editorial = editorialService.findById(IDeditorial).get();
        return new ResponseEntity<Editorial>(editorial, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Editorial editorial) {
        if (StringUtils.isBlank(editorial.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (editorialService.existsByName(editorial.getName()))
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        editorialService.save(editorial);
        return new ResponseEntity(new Message("editorial guardada"), HttpStatus.CREATED);
    }

    @PostMapping("/modify/{IDeditorial}")
    public ResponseEntity<?> update(@PathVariable Integer IDeditorial, @RequestBody Editorial editorial) {
        if (!editorialService.existsById(IDeditorial))
            return new ResponseEntity(new Message("no existe esa editorial"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(editorial.getName()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (editorialService.existsByName(editorial.getName()) && editorialService.findByName(editorial.getName()).get().getIDeditorial() != IDeditorial)
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Editorial editorialUpdate = editorialService.findById(IDeditorial
        ).get();
        editorialUpdate.setName(editorial.getName());
        editorialService.save(editorialUpdate);
        return new ResponseEntity(new Message("editorial actualizada"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{IDeditorial}")
    public ResponseEntity<?> delete(@PathVariable Integer IDeditorial) {
        if (!editorialService.existsById(IDeditorial))
            return new ResponseEntity(new Message("no existe esa editorial"), HttpStatus.NOT_FOUND);
        editorialService.deleteById(IDeditorial);
        return new ResponseEntity(new Message("editorial eliminada"), HttpStatus.OK);
    }

}