package szfm.krankenwagenracing.admin_user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import szfm.krankenwagenracing.admin_user.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class CostumeUsersDetailsTest {
    private CustomUserDetail customUserDetails;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("john.doe@example.com", "John Doe", "password123", "ROLE_USER");
        customUserDetails = new CustomUserDetail(user);
    }

    @Test
    void testGetUsername() {
        // Ellenőrizzük, hogy a felhasználónév (email) megfelelő
        assertEquals("john.doe@example.com", customUserDetails.getUsername());
    }

    @Test
    void testGetPassword() {
        // Ellenőrizzük, hogy a jelszó megfelelő
        assertEquals("password123", customUserDetails.getPassword());
    }

    @Test
    void testGetAuthorities() {
        // Ellenőrizzük, hogy a szerep 'ROLE_USER'
        assertTrue(customUserDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testGetFullname() {
        // Ellenőrizzük, hogy a teljes név megfelelő
        assertEquals("John Doe", customUserDetails.getFullname());
    }

    @Test
    void testAccountNonExpired() {
        // Ellenőrizzük, hogy a fiók nem lejárt
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    void testAccountNonLocked() {
        // Ellenőrizzük, hogy a fiók nem zárolt
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    void testCredentialsNonExpired() {
        // Ellenőrizzük, hogy a hitelesítő adatok nem lejártak
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        // Ellenőrizzük, hogy a felhasználó engedélyezett
        assertTrue(customUserDetails.isEnabled());
    }
}
