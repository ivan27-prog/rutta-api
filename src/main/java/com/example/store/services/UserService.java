/*
package com.example.store.services;

import com.example.store.dtos.*;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.*;
import com.example.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado"));
    }

    public User createUser(UserRequest r) {
        User u = new User();
        u.setUserName(r.userName());
        u.setEmail(r.email());
        u.setPassword(passwordEncoder.encode(r.password()));
        u.setPhoneNumber(r.phoneNumber());
        return repository.save(u);
    }

    public User updateUser(Integer id, UserRequest r) {
        User u = getUserById(id);
        if (r.userName() != null) u.setUserName(r.userName());
        if (r.email() != null) u.setEmail(r.email());
        if (r.password() != null) u.setPassword(passwordEncoder.encode(r.password()));
        if (r.phoneNumber() != null) u.setPhoneNumber(r.phoneNumber());
        return repository.save(u);
    }

    public User deleteUserById(Integer id) {
        User u = getUserById(id);
        repository.delete(u);
        return u;
    }
}
*/