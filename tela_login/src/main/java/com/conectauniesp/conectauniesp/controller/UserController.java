package com.conectauniesp.conectauniesp.controller;

import com.conectauniesp.conectauniesp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getMatriculaOuEmail(),
                loginRequest.getSenha());
    }
}
