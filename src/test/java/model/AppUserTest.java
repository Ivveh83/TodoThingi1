package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    @Test
    void testConstructorValidInput() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        assertEquals("anna", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    void testConstructorNullUsernameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser(null, "pass123", AppRole.ROLE_APP_USER);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
    }

    @Test
    void testConstructorEmptyUsernameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("", "pass123", AppRole.ROLE_APP_USER);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
    }

    @Test
    void testConstructorBlankUsernameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("   ", "pass123", AppRole.ROLE_APP_USER);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
    }

    @Test
    void testConstructorNullPasswordThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("anna", null, AppRole.ROLE_APP_USER);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
    }

    @Test
    void testConstructorNullRoleThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("anna", "pass123", null);
        });
        assertEquals("Not allowed to be null", exception.getMessage());
    }

    @Test
    void testSetUsernameValid() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        user.setUsername("maria");
        assertEquals("maria", user.getUsername());
    }

    @Test
    void testSetUsernameNullThrowsException() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername(null);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
        assertEquals("anna", user.getUsername());
    }

    @Test
    void testSetUsernameEmptyThrowsException() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
        assertEquals("anna", user.getUsername());
    }

    @Test
    void testSetPasswordValid() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        user.setPassword("newpass456");
        assertEquals("newpass456", user.getPassword());
    }

    @Test
    void testSetPasswordNullThrowsException() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });
        assertEquals("Not allowed to be empty or null", exception.getMessage());
        assertEquals("pass123", user.getPassword());
    }

    @Test
    void testSetRoleValid() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        user.setRole(AppRole.ROLE_APP_ADMIN);
        assertEquals(AppRole.ROLE_APP_ADMIN, user.getRole());
    }

    @Test
    void testSetRoleNullThrowsException() {
        AppUser user = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setRole(null);
        });
        assertEquals("Not allowed to be null", exception.getMessage());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        AppUser user1 = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("anna", "different", AppRole.ROLE_APP_USER);
        assertEquals(user1, user2); // equals ignorerar password
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}