package br.com.redeuniesp.api.service;

import br.com.redeuniesp.api.model.User;
import br.com.redeuniesp.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User findPersonAuthtenticated() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nome;

        if (authentication instanceof UserDetails) {
            nome = ((UserDetails)authentication).getUsername();
            System.out.println("nome do usuario: " + nome);
        } else {
            nome = authentication.toString();
            System.out.println("nome do usuario: " + nome);
        }
        var user = userRepository.findByUserName(nome);
        return user;
    }


}


