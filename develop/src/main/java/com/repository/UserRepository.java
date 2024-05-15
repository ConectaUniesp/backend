package com.conectauniesp.conectauniesp.repository;

import com.conectauniesp.conectauniesp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByMatriculaOrEmail(String matricula, String email);
}
