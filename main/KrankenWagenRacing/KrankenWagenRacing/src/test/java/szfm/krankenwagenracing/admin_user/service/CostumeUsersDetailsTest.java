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
        assertEquals("john.doe@example.com", customUserDetails.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", customUserDetails.getPassword());
    }

    @Test
    void testGetAuthorities() {
        assertTrue(customUserDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testGetFullname() {
        assertEquals("John Doe", customUserDetails.getFullname());
    }

    @Test
    void testAccountNonExpired() {
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    void testAccountNonLocked() {
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    void testCredentialsNonExpired() {
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(customUserDetails.isEnabled());
    }
}
