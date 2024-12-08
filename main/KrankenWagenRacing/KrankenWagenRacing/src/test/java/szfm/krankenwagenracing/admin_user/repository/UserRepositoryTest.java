package szfm.krankenwagenracing.admin_user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.service.UserServicempl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServicempl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test@example.com", "Test User", "password", "USER");
    }

    @Test
    void testFindByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User foundUser = userRepository.findByEmail("test@example.com");

        assertNotNull(foundUser, "Felhasználó nem található");
        assertEquals("test@example.com", foundUser.getEmail(), "A felhasználó e-mail címe nem megfelelő");
    }

    @Test
    void testFindByEmail2() {
        when(userRepository.findByEmail2("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByEmail2("test@example.com");

        assertTrue(foundUser.isPresent(), "Felhasználó nem található");
        assertEquals("test@example.com", foundUser.get().getEmail(), "A felhasználó e-mail címe nem megfelelő");
    }

    @Test
    void testFindByEmail_NotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        User foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertNull(foundUser, "Nem kellene felhasználót találni");
    }

    @Test
    void testFindByEmail2_NotFound() {
        when(userRepository.findByEmail2("nonexistent@example.com")).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepository.findByEmail2("nonexistent@example.com");

        assertFalse(foundUser.isPresent(), "Nem kellene felhasználót találni");
    }
}