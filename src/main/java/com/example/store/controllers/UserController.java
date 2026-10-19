package com.example.store.controllers;

import com.example.store.dtos.UserRequest;
import com.example.store.models.User;
import com.example.store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") Integer id) {
        return service.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        User created = service.createUser(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable("userId") Integer id, @RequestBody UserRequest request) {
        return service.updateUser(id, request);
    }

    @DeleteMapping(path = "{userId}")
    public User deleteUserById(@PathVariable("userId") Integer id) {
        return service.deleteUserById(id);
    }
}
