package Day20;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserValidatorTest {

    private UserValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new UserValidator();
    }

    // 🧪 Test Suite for your exact Valid Email Requirements
    @ParameterizedTest
    @ValueSource(strings = {
        "abc@yahoo.com",
        "abc-100@yahoo.com",
        "abc.100@yahoo.com",
        "abc111@abc.com",
        "abc-100@abc.net",
        "abc.100@abc.com.au",
        "abc@1.com",
        "abc@gmail.com.com",
        "abc+100@gmail.com"
    })
    public void givenValidEmails_WhenChecked_ShouldReturnTrue(String email) {
        boolean result = validator.validateEmail(email);
        assertTrue(result, "Failed validation for valid email: " + email);
    }

    // 🧪 Test Suite for your exact Invalid Email Requirements
    @ParameterizedTest
    @ValueSource(strings = {
        "abc",                  // No @ symbol
        "abc@.com.my",           // TLD cannot start with a dot
        "abc123@gmail.a",       // Last TLD must be at least 2 chars
        "abc123@.com",          // TLD cannot start with a dot
        "abc123@.com.com",      // TLD cannot start with a dot
        ".abc@abc.com",         // Cannot start with a dot
        "abc()*@gmail.com",     // Special characters not allowed in local part
        "abc@%*.com",           // Special characters not allowed in domain
        "abc..2002@gmail.com",  // Double dots not allowed
        "abc.@gmail.com",       // Cannot end local part with a dot
        "abc@abc@gmail.com",    // Double @ not allowed
        "abc@gmail.com.1a",     // TLD cannot contain digits
        "abc@gmail.com.aa.au"   // Exceeds allowed TLD depth layers
    })
    public void givenInvalidEmails_WhenChecked_ShouldReturnFalse(String email) {
        boolean result = validator.validateEmail(email);
        assertFalse(result, "Failed to catch invalid email: " + email);
    }
}
