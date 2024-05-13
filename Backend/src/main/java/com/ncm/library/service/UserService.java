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

  public Iterable<User> getAll() {
    Iterable<User> lista = userRepository.findAll();
    return lista;
  }

  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByName(String name) {
    return userRepository.findByName(name);
  }

  // public Optional<User> findByEmail(String email) {
  //   return userRepository.findByEmail(email);
  // }

  public void save(User user) {
    userRepository.save(user);
  }

  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  public boolean existsName(String name) {
    return userRepository.existsName(name);
  }

  public boolean existsId(Integer id) {
    return userRepository.existsById(id);
  }

  // public boolean existsEmail(String email) {
  //   return userRepository.existsEmail(email);
  // }
}
