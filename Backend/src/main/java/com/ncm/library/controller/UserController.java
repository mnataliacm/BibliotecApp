package com.ncm.library.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ncm.library.entity.User;
import com.ncm.library.service.UserService;
import com.ncm.library.dto.Message;

@RestController
@RequestMapping("/api/library/user")
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

  @GetMapping("/count")
  public ResponseEntity<Long> count() {
    long count = userService.count();
    return new ResponseEntity<Long>(count, HttpStatus.OK);
  }

  @GetMapping("/detail/{IDuser}")
  public ResponseEntity<User> getOne(@PathVariable Integer IDuser) {
    if (!userService.existsById(IDuser))
      return new ResponseEntity(new Message("no existe ese usuario"), HttpStatus.NOT_FOUND);
    User user = userService.findById(IDuser).get();
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

  @PutMapping("/modify/{IDuser}")
  public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer IDuser) {
    if (!userService.existsById(IDuser))
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
        userService.findByEmail(user.getEmail()).get().getIDuser() != IDuser)
      return new ResponseEntity(new Message("ese email ya existe"),
          HttpStatus.BAD_REQUEST);
    User userUpdate = userService.findById(IDuser).get();
    userUpdate.setMember(user.getMember());
    userUpdate.setName(user.getName());
    userUpdate.setSurname(user.getSurname());
    userUpdate.setEmail(user.getEmail());
    userUpdate.setMobile(user.getMobile());
    userUpdate.setAddress(user.getAddress());
    userUpdate.setNum(user.getNum());
    userUpdate.setFloor(user.getFloor());
    userUpdate.setCP(user.getCP());
    userUpdate.setTown(user.getTown());
    userUpdate.setCity(user.getCity());
    userService.save(userUpdate);
    return new ResponseEntity(new Message("usuario actualizado"),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{IDuser}")
  public ResponseEntity<?> delete(@PathVariable Integer IDuser) {
    if (!userService.existsById(IDuser))
      return new ResponseEntity(new Message("no existe ese usuario"),
          HttpStatus.NOT_FOUND);
    userService.deleteById(IDuser);
    return new ResponseEntity(new Message("usuario eliminado"), HttpStatus.OK);
  }
}
