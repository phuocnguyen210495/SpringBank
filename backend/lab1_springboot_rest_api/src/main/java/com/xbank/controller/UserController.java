package com.xbank.controller;

import com.xbank.domain.User;
import com.xbank.dto.UserDTO;
import com.xbank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.CREATED);
    }

    @GetMapping("/api/user/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable(value = "name") String name) {
        try {
            return new ResponseEntity<>(userService.getUserByFullName(name), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") long id) {
        return new ResponseEntity<>(userService.updateUser(userDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.CREATED);
    }
}