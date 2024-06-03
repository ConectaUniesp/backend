package br.com.redeuniesp.api.controller;

import br.com.redeuniesp.api.model.dto.security.AccountCredentialsDTO;
import br.com.redeuniesp.api.model.dto.security.UserCredentialsDTO;
import br.com.redeuniesp.api.service.AuthService;
import br.com.redeuniesp.api.service.AuthUserService;
import br.com.redeuniesp.api.service.FileStorageUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autheticação de usuario", description = "Endpoints para gerenciar usuarios")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthService authService;

    @Autowired
    private FileStorageUserService fileStorageService;


    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    @Operation(summary = "Autenticar usuario e retornar um token")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
        return authUserService.signin(data);
    }

    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{username}")
    @Operation(summary = "Atualizar token para usuário autenticado e retornar um token")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        var token = authUserService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @PostMapping("/signup")
    @Operation(summary = "Cadastrar um usuario")
    public ResponseEntity<Void> signUpUser(@RequestPart UserCredentialsDTO credentials,
                                           @RequestPart MultipartFile enroll) {
        var obj = authUserService.signUp(credentials, enroll);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
