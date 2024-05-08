package com.conectauniesp.conectauniesp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {


    private String matriculaOuEmail;
    private String senha;
}
