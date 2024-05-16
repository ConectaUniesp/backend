package br.com.redeuniesp.api.service;

import br.com.redeuniesp.api.model.Declaracao;
import br.com.redeuniesp.api.model.User;
import br.com.redeuniesp.api.model.dto.UploadFileResponseDTO;
import br.com.redeuniesp.api.model.dto.security.AccountCredentialsDTO;
import br.com.redeuniesp.api.model.dto.security.TokenDTO;
import br.com.redeuniesp.api.model.dto.security.UserCredentialsDTO;
import br.com.redeuniesp.api.model.enums.Role;
import br.com.redeuniesp.api.repository.AuthRepository;
import br.com.redeuniesp.api.repository.DeclaracaoRepository;
import br.com.redeuniesp.api.repository.UserRepository;
import br.com.redeuniesp.api.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AuthUserService {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private DeclaracaoRepository declaracaoRepository;

    @Autowired
    private FileStorageUserService fileStorageUserService;



    private static final Logger logger = Logger.getLogger(AuthUserService.class.getName());

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            var user = authRepository.findByEmail(username);

            var tokenResponse = new TokenDTO();
            if (user != null) {
                List<String> roles = new ArrayList<>();
                roles.add(Role.USER.name());
                tokenResponse = tokenProvider.createAccessToken(username, roles);

            } else {

                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }
    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUserName(username);

        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

    public User signUp(UserCredentialsDTO data, MultipartFile enroll) {

        data.setPassword(passwordEncoder(data));
        User obj = fromDTO(data);
        Declaracao declaracao = fileFromDTO(uploadFile(enroll, data));
        declaracaoRepository.save(declaracao);
        obj.setRegistrationStatement(declaracao);
        userRepository.save(obj);

        return obj;
    }

    public User fromDTO(UserCredentialsDTO objDto) {
        return new User(objDto.getEmail(), objDto.getPassword(), Role.USER,
                true, true, true, true, objDto.getEnroll());
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }

    private String passwordEncoder(UserCredentialsDTO data){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(data.getPassword());
    }

    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file, UserCredentialsDTO data){
        var filename = fileStorageUserService.storeFile(file, data.getEmail());
        String path = String.valueOf(fileStorageUserService.getFileStorageLocation());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadfile/")
                .path(filename)
                .toUriString();
        return new UploadFileResponseDTO(filename, fileDownloadUri, file.getContentType(), file.getSize(), path);
    }

    public Declaracao fileFromDTO(UploadFileResponseDTO file){
        Declaracao declaracaoFile = new Declaracao();
        declaracaoFile.setFileName(file.getFileName());
        declaracaoFile.setPath(file.getPath());
        return declaracaoFile;
    }
}


