package szfm.krankenwagenracing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import szfm.krankenwagenracing.admin_user.service.UserService;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class KrankenWagenRacingApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUserServiceNotNull() {
        assertNotNull(userService, "A UserService bean nem lett példányosítva");
    }
}