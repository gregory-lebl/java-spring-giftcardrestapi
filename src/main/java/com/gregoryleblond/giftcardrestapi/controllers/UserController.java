package com.gregoryleblond.giftcardrestapi.controllers;

import com.gregoryleblond.giftcardrestapi.models.User;
import com.gregoryleblond.giftcardrestapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }

    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }
}
