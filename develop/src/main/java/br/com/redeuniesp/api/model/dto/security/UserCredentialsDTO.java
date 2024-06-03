package br.com.redeuniesp.api.model.dto.security;


import br.com.redeuniesp.api.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String password;
    private String enroll;
    private Role role;

    public UserCredentialsDTO(String email, String password, String enroll, Role role) {
        this.email = email;
        this.password = password;
        this.enroll = enroll;

    }
}
