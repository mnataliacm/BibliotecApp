package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ncm.library.entity.User;
import com.ncm.library.service.UserService;
import com.ncm.library.dto.Message;

// @Controller // This means that this class is a Controller
// @RequestMapping(path="/library")
@RestController
@RequestMapping("/api/library/user")
// @RequestMapping("/library")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public ResponseEntity<Iterable<User>> getAllUsers() {
    Iterable<User> people = userService.findAll();
    return new ResponseEntity<Iterable<User>>(people, HttpStatus.OK);
  }

  @GetMapping("/detail/{id}")
  public ResponseEntity<User> getOne(@PathVariable Integer id) {
    if (!userService.existsById(id))
      return new ResponseEntity(new Message("no existe ese usuario"), HttpStatus.NOT_FOUND);
    User user = userService.findById(id).get();
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<?> create(@RequestBody User user) {
    if (StringUtils.isBlank(user.getName()))
      return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getSurname()))
      return new ResponseEntity(new Message("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getEmail()))
      return new ResponseEntity(new Message("el email es obligatorio"), HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getMobile()))
      return new ResponseEntity(new Message("el móvil es obligatorio"), HttpStatus.BAD_REQUEST);
    if (userService.existsByEmail(user.getEmail()))//
      return new ResponseEntity(new Message("ese email ya existe"),
          HttpStatus.BAD_REQUEST);

    userService.save(user);
    return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
  }

  @PutMapping("/modify/{id}")
  public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
    if (!userService.existsById(id))
      return new ResponseEntity(new Message("no existe ese usuario"),
          HttpStatus.NOT_FOUND);
    if (StringUtils.isBlank(user.getName()))
      return new ResponseEntity(new Message("el nombre es obligatorio"),
          HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getSurname()))
      return new ResponseEntity(new Message("el apellido es obligatorio"),
          HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getEmail()))
      return new ResponseEntity(new Message("el email es obligatorio"),
          HttpStatus.BAD_REQUEST);
    if (StringUtils.isBlank(user.getMobile()))
      return new ResponseEntity(new Message("el móvil es obligatorio"),
          HttpStatus.BAD_REQUEST);
    if (userService.existsByEmail(user.getEmail()) &&
        userService.findByEmail(user.getEmail()).get().getId() != id)
      return new ResponseEntity(new Message("ese email ya existe"),
          HttpStatus.BAD_REQUEST);
    User userUpdate = userService.findById(id).get();
    userUpdate.setName(user.getName());
    userUpdate.setSurname(user.getSurname());
    userUpdate.setEmail(user.getEmail());
    userUpdate.setMobile(user.getMobile());
    userUpdate.setAddress(user.getAddress());
    userUpdate.setNum(user.getNum());
    userUpdate.setFloor(user.getFloor());
    userUpdate.setCP(user.getCP());
    userUpdate.setCity(user.getCity());
    userService.save(userUpdate);
    return new ResponseEntity(new Message("usuario actualizado"),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    if (!userService.existsById(id))
      return new ResponseEntity(new Message("no existe ese usuario"),
          HttpStatus.NOT_FOUND);
    userService.deleteById(id);
    return new ResponseEntity(new Message("usuario eliminado"), HttpStatus.OK);
  }
}
