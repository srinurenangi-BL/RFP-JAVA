package Day21;

package com.userregistration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationTest {

    private UserRegistrationValidator validator;

    @BeforeEach
    public void setupService() {
        validator = new UserRegistrationValidator();
    }

    // --- FIRST NAME TESTING (UC 1) ---
    @Test
    public void givenFirstName_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateFirstName("John"));
    }

    @Test
    public void givenFirstName_WhenInvalid_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateFirstName("jo");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_FIRST_NAME, exception.getType());
    }

    // --- LAST NAME TESTING (UC 2) ---
    @Test
    public void givenLastName_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateLastName("Doe"));
    }

    @Test
    public void givenLastName_WhenInvalid_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateLastName("smith");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_LAST_NAME, exception.getType());
    }

    // --- MOBILE NUMBER TESTING (UC 4) ---
    @Test
    public void givenMobileNumber_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateMobile("91 9919819801"));
    }

    @Test
    public void givenMobileNumber_WhenMissingSpaceOrDigits_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateMobile("919919819801");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_MOBILE, exception.getType());
    }

    // --- PASSWORD TESTING (UC 5 - UC 8) ---
    @Test
    public void givenPassword_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validatePassword("Secure1!"));
    }

    @Test
    public void givenPassword_WhenMultipleSpecialCharacters_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validatePassword("Secure1!!"); // Fails: Contains 2 special characters
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_PASSWORD, exception.getType());
    }
}
