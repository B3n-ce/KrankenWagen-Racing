package szfm.krankenwagenracing.admin_user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import szfm.krankenwagenracing.admin_user.dto.UserDto;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServicempl userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto("testuser@example.com", "Test User", "password123", "USER");
    }

    @Test
    void testSave() {
        User user = new User(userDto.getEmail(), userDto.getFullname(), "encodedPassword", userDto.getRole());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(userDto);

        assertNotNull(savedUser, "A mentett felhasználó nem lehet null");
        assertEquals("Test User", savedUser.getFullname(), "A felhasználó neve nem egyezik");
    }

    @Test
    void testUpdateFullName() {
        User user = new User("testuser@example.com", "Test User", "password123", "USER");
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.updateFullName("testuser@example.com", "New Name");
        assertEquals("New Name", user.getFullname(), "A név nem frissült helyesen");
        verify(userRepository).save(user);
    }

    @Test
    void testEmailExists() {
        when(userRepository.findByEmail2(anyString())).thenReturn(java.util.Optional.of(new User()));

        boolean exists = userService.emailExists("testuser@example.com");

        assertTrue(exists, "Az e-mail nem található az adatbázisban");
    }
}