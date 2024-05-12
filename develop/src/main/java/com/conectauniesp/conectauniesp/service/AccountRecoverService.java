import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void generateRecoveryToken(User user) {
        String recoveryToken = generateRandomToken();
        user.setRecoveryToken(recoveryToken);
        userRepository.save(user);
    }

    public User findByRecoveryToken(String recoveryToken) {
        return userRepository.findByRecoveryToken(recoveryToken);
    }

    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        user.setRecoveryToken(null);
        userRepository.save(user);
    }

    private String generateRandomToken() {

    }
}
