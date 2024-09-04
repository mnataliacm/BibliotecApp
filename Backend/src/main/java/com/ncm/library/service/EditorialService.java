package com.ncm.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncm.library.entity.Editorial;
import com.ncm.library.repository.EditorialRepository;

@Service
@Transactional
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public Iterable<Editorial> findAll() {
        return editorialRepository.findAll();
    }

    public long count() {
        return editorialRepository.count();
    }

    public Optional<Editorial> findById(Integer IDeditorial) {
        return editorialRepository.findById(IDeditorial);
    }

        public Optional<Editorial> findByName(String name) {
        return editorialRepository.findByName(name);
    }
    
    public boolean existsById(Integer IDeditorial) {
        return editorialRepository.existsById(IDeditorial);
    }

    public boolean existsByName(String name) {
        return editorialRepository.existsByName(name);
    }

    public void save(Editorial editorial) {
        editorialRepository.save(editorial);
    }

    public void deleteById(Integer IDeditorial) {
        editorialRepository.deleteById(IDeditorial);
    }



}