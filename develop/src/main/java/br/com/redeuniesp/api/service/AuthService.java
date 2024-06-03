package br.com.redeuniesp.api.service;

import br.com.redeuniesp.api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthService implements UserDetailsService {

    private Logger logger = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("procurando um usuario pelo nome" + username + "!");
        var auth = authRepository.findByEmail(username);
        if (auth != null) {
            return auth;
        } else {
            throw new UsernameNotFoundException("UserName" + username + "not found!");
        }
    }


}