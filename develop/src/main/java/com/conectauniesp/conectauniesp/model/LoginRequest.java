package com.conectauniesp.conectauniesp.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LoginRequest {


    private String matriculaOuEmail;
    private String senha;
}
