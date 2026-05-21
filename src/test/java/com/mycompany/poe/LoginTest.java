package com.mycompany.poe;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testValidUsername() {
        Login login = new Login();
        assertTrue(login.checkUserName("user_"));
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login();
        assertFalse(login.checkUserName("usernameTooLong"));
    }

    @Test
    public void testValidPassword() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Password123!"));
    }

    @Test
    public void testInvalidPassword() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("weak"));
    }

    @Test
    public void testValidCellNumber() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27718693002"));
    }

    @Test
    public void testInvalidCellNumber() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("0718693002"));
    }

    @Test
    public void testSuccessfulRegistration() {
        Login login = new Login();
        String result = login.registerUser("user_", "Password123!", "+27718693002", "John", "Doe");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testSuccessfulLogin() {
        Login login = new Login();
        login.registerUser("user_", "Password123!", "+27718693002", "John", "Doe");
        assertTrue(login.loginUser("user_", "Password123!"));
    }

    @Test
    public void testFailedLogin() {
        Login login = new Login();
        login.registerUser("user_", "Password123!", "+27718693002", "John", "Doe");
        assertFalse(login.loginUser("user_", "WrongPassword"));
    }
}
