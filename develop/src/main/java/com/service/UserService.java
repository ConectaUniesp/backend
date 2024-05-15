package com.conectauniesp.conectauniesp.service;

import com.conectauniesp.conectauniesp.model.User;
import com.conectauniesp.conectauniesp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User user){
        userRepository.save(user);
    }

    public User login(String matriculaOuEmail, String senha) {
        User user = userRepository.findByMatriculaOrEmail(matriculaOuEmail, matriculaOuEmail);
        if (user != null && user.getSenha().equals(senha)) {
            return user;
        }
        return null; // Login inválido
    }

    //fazer comparações de objetos User
    public boolean compararUsuarios(User user1, User user2) {
        return user1.equals(user2);
    }
}


