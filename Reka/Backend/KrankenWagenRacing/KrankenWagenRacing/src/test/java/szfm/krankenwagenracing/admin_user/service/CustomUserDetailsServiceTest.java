package szfm.krankenwagenracing.admin_user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository; // Mockoljuk a UserRepository-t

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService; // A szolgáltatás, amit tesztelünk

    private User user;

    @BeforeEach
    void setUp() {
        // Inicializáljuk a mockokat
        MockitoAnnotations.openMocks(this);

        // Létrehozunk egy User objektumot
        user = new User("john.doe@example.com", "John Doe", "password123", "ROLE_USER");
    }

    @Test
    void testLoadUserByUsername() {
        // Mockoljuk, hogy a UserRepository visszaadja a felhasználót
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(user);

        // Meghívjuk a loadUserByUsername metódust
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("john.doe@example.com");

        // Ellenőrizzük, hogy a felhasználó helyesen van betöltve
        assertEquals("john.doe@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mockoljuk, hogy a UserRepository nem találja a felhasználót
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Ellenőrizzük, hogy a UsernameNotFoundException kivételt dob
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("nonexistent@example.com"));
    }
}