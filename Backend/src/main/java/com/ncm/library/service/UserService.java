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

  public Iterable<User> findAll() {
    Iterable<User> list = userRepository.findAll();
    return list;
  }

  public long count() {
    long count = userRepository.count();
    return count;
  }

  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByEmail(String np) {
    return userRepository.findByEmail(np);
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }

  public boolean existsById(Integer id) {
    return userRepository.existsById(id);
  }

  public boolean existsByEmail(String np) {
    return userRepository.existsByEmail(np);
  }
}
