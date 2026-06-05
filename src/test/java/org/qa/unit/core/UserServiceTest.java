package org.qa.unit.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qa.core.*;
import org.qa.models.AuthToken;
import org.qa.models.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    EmailService emailService;

    @Mock
    TokenService tokenService;

    @InjectMocks
    UserService userService;

    @Test
    void successfulRegistration() {
        // Arrange
        User mockUser = new User("USR001", "test@mail.com", "hashed123", "user", false);

        when(userRepository.findByEmail("test@mail.com")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("hashed123");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        User result = userService.register("test@mail.com", "password123");

        // Assert
        assertNotNull(result);
        assertEquals("test@mail.com", result.getEmail());
        assertFalse(result.isActive());

        verify(userRepository).findByEmail("test@mail.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
        verify(emailService).sendVerificationEmail("test@mail.com", "CODE123");
    }

    @Test
    void registrationWithBusyEmail(){
        // Arange
        User existingUser = new User("JD32", "tesst@mail.com", "hashed3939", "admin", true);
        when(userRepository.findByEmail("occupied@mail.com")).thenReturn(existingUser);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("occupied@mail.com", "password");
        });
        assertEquals("Email already registered", exception.getMessage());
    }

    @Test
    void successfulLogin() {
        // Arrange
        User user = new User("USR001", "user@mail.com", "hashed123", "user", true);
        AuthToken authToken = new AuthToken("TOKEN123", "USR001", LocalDateTime.now().plusHours(1));

        when(userRepository.findByEmail("user@mail.com")).thenReturn(user);
        when(passwordEncoder.matches("password123", "hashed123")).thenReturn(true);
        when(tokenService.generateToken("USR001")).thenReturn(authToken);

        // Act
        AuthToken result = userService.login("user@mail.com", "password123");

        // Assert
        assertNotNull(result);
        assertEquals("TOKEN123", result.getToken());
        verify(userRepository).findByEmail("user@mail.com");
        verify(passwordEncoder).matches("password123", "hashed123");
        verify(tokenService).generateToken("USR001");
    }

    @Test
    void loginWithAnIncorrectPassword() {
        // Arrange
        User user = new User("USR001", "user@mail.com", "hashed123", "user", true);
        when(userRepository.findByEmail("user@mail.com")).thenReturn(user);
        when(passwordEncoder.matches("wrongpassword", "hashed123")).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.login("user@mail.com", "wrongpassword");
        });
        assertEquals("Invalid password", exception.getMessage());
    }


}