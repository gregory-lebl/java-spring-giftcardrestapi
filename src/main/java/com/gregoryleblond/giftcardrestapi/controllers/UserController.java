package com.gregoryleblond.giftcardrestapi.controllers;

import com.gregoryleblond.giftcardrestapi.dto.UserLoginDto;
import com.gregoryleblond.giftcardrestapi.models.User;
import com.gregoryleblond.giftcardrestapi.repositories.UserRepository;
import com.gregoryleblond.giftcardrestapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserLoginDto login(@RequestBody User user) {
        return service.loginUser(user);
    }
}
