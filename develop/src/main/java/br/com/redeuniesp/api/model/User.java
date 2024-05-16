package br.com.redeuniesp.api.model;

import br.com.redeuniesp.api.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;


@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auth{
    private static final long serialVersionUID = 1L;

        @Column(nullable = false)
        private String enroll;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Declaracao registrationStatement;

    public User(String email, String password, Role role, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, String enroll) {
        super(email, password, role, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.enroll = enroll;
    }
}

