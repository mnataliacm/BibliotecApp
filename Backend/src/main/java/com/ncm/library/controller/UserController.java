package com.ncm.library.controller;

// import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ncm.library.entity.User;
import com.ncm.library.service.UserService;
import com.ncm.library.dto.Message;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "http://localhost:4200")
// @SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/all")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		Iterable<User> people = userService.getAll();
		return new ResponseEntity<Iterable<User>>(people, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/detalle/{id}")
	public ResponseEntity<User> getOne(@PathVariable Integer id) {
		if (!userService.existsId(id))
			return new ResponseEntity(new Message("no existe ese user"),
					HttpStatus.NOT_FOUND);
		User user = userService.findById(id).get();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// @PostMapping("/add")
	// public ResponseEntity<?> create(@RequestBody User user) {
	// 	if (StringUtils.isBlank(user.getName()))
	// 		return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getSurname()))
	// 		return new ResponseEntity(new Message("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getEmail()))
	// 		return new ResponseEntity(new Message("el email es obligatorio"), HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getMobile()))
	// 		return new ResponseEntity(new Message("el móvil es obligatorio"), HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getAddress()))
	// 		return new ResponseEntity(new Message("el dirección es obligatoria"), HttpStatus.BAD_REQUEST);
	// 	if (userService.existsEmail(user.getEmail())) // Revisar en user service
	// 		return new ResponseEntity(new Message("ese email ya existe"),
	// 				HttpStatus.BAD_REQUEST);
	// 	userService.save(user);
	// 	return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
	// }

	// @PutMapping("/user/modify/{id}")
	// public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Integer id) {
	// 	if (!userService.existsId(id))
	// 		return new ResponseEntity(new Message("no existe ese user"),
	// 				HttpStatus.NOT_FOUND);
	// 	if (StringUtils.isBlank(user.getName()))
	// 		return new ResponseEntity(new Message("el nombre es obligatorio"),
	// 				HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getSurname()))
	// 		return new ResponseEntity(new Message("el apellido es obligatorio"),
	// 				HttpStatus.BAD_REQUEST);
	// 	if (StringUtils.isBlank(user.getEmail()))
	// 		return new ResponseEntity(new Message("el email es obligatorio"),
	// 				HttpStatus.BAD_REQUEST);
	// 	if (userService.existsEmail(user.getEmail()) &&
	// 			userService.findByEmail(user.getEmail()).get().getId() != id)
	// 		return new ResponseEntity(new Message("ese email ya existe"),
	// 				HttpStatus.BAD_REQUEST);
	// 	User prodUpdate = userService.findById(id).get();
	// 	prodUpdate.setName(user.getName());
	// 	prodUpdate.setSurname(user.getSurname());
	// 	prodUpdate.setEmail(user.getEmail());
	// 	prodUpdate.setMobile(user.getMobile());
	// 	prodUpdate.setAddress(user.getAddress());
	// 	prodUpdate.setNum(user.getNum());
	// 	prodUpdate.setFloor(user.getFloor());
	// 	prodUpdate.setCP(user.getCP());
	// 	prodUpdate.setCP(user.getCP());
	// 	userService.save(prodUpdate);
	// 	return new ResponseEntity(new Message("usuario actualizado"),
	// 			HttpStatus.CREATED);
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		if (!userService.existsId(id))
			return new ResponseEntity(new Message("no existe ese usuario"), HttpStatus.NOT_FOUND);
		userService.delete(id);
		return new ResponseEntity(new Message("usuario eliminado"), HttpStatus.OK);
	}
}
