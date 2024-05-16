package br.com.redeuniesp.api.controller;

import br.com.redeuniesp.api.model.dto.LoginRequestDTO;
import br.com.redeuniesp.api.model.User;
import br.com.redeuniesp.api.service.UserService;
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


}
