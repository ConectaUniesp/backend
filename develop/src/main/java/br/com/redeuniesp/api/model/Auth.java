package br.com.redeuniesp.api.model;

import br.com.redeuniesp.api.model.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Auth implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at")
    @CurrentTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updateAt;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column
    private Boolean enabled;



    public Auth() {}

    public Auth(String email, String password, Role role, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public Date getCreatedAt() {
        return createdAt;
    }



    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Date getUpdateAt() {
        return updateAt;
    }



    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }



    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }



    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }



    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }



    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }



    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }



    public Boolean getEnabled() {
        return enabled;
    }



    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Auth other = (Auth) obj;
        return Objects.equals(id, other.id);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



    @Override
    public String getUsername() {
        return this.email;
    }



    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }



    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }



    @Override
    public boolean isEnabled() {
        return this.enabled;
    }






}
