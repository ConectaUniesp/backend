package br.com.redeuniesp.api.repository;

import br.com.redeuniesp.api.model.Auth;
import br.com.redeuniesp.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    @Query("SELECT u FROM Auth u WHERE u.email = :email" )
    Auth findByEmail(@Param("email") String email);
}
