package com.controller;

import com.conectauniesp.conectauniesp.model.LoginRequest;
import com.conectauniesp.conectauniesp.model.User;
import com.conectauniesp.conectauniesp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getMatriculaOuEmail(),
                loginRequest.getSenha());
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user){
        userService.create(user);
    }
}
