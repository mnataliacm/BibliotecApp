package com.ncm.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncm.library.entity.User;
import com.ncm.library.repository.UserRepository;

@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAll(){
        Iterable<User> lista = userRepository.findAll();
        return lista;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String np){
        return userRepository.findByName(np);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public boolean existsName(String np){
        return ((UserService) userRepository).existsName(np);
    }

    public boolean existsById(Integer id){
        return userRepository.existsById(id);
    }
}
