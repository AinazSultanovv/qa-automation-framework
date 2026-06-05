package org.qa.core;

import org.qa.models.AuthToken;
import org.qa.models.User;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public User register(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        String pashash = passwordEncoder.encode(password);

        User user = new User("01", email, pashash, "admin", false);
        userRepository.save(user);
        emailService.sendVerificationEmail(email, "CODE123");
        return user;
    }
    public AuthToken login(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("Не найден");
        } else if (!user.isActive()) {
            throw new IllegalArgumentException("Account not activated");
        } else if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid password");
        }
        AuthToken token = tokenService.generateToken(user.getId());
        return token;
    }
}
