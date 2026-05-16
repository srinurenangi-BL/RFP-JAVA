package Day23;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserRegistrationTest {

    private UserRegistrationValidator validator;

    @Before
    public void setupService() {
        validator = new UserRegistrationValidator();
    }

    // --- HAPPY / SAD FIRST NAME ---
    @Test
    public void givenFirstName_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateFirstName.validate("John"));
    }

    @Test
    public void givenFirstName_WhenInvalid_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateFirstName.validate("jo");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_FIRST_NAME, exception.getType());
    }

    // --- HAPPY / SAD LAST NAME ---
    @Test
    public void givenLastName_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateLastName.validate("Doe"));
    }

    @Test
    public void givenLastName_WhenInvalid_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateLastName.validate("smith");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_LAST_NAME, exception.getType());
    }

    // --- HAPPY / SAD MOBILE ---
    @Test
    public void givenMobileNumber_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validateMobile.validate("91 9919819801"));
    }

    @Test
    public void givenMobileNumber_WhenMissingSpace_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateMobile.validate("919919819801");
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_MOBILE, exception.getType());
    }

    // --- HAPPY / SAD PASSWORD ---
    @Test
    public void givenPassword_WhenValid_ShouldReturnTrue() throws UserRegistrationException {
        assertTrue(validator.validatePassword.validate("Secure1!"));
    }

    @Test
    public void givenPassword_WhenMultipleSpecialCharacters_ShouldThrowCustomException() {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validatePassword.validate("Secure1!!"); 
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_PASSWORD, exception.getType());
    }
}
