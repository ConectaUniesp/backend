package br.com.redeuniesp.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String matriculaOuEmail;
    private String senha;
}
