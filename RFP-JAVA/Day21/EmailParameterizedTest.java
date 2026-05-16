package Day21;

package com.userregistration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EmailParameterizedTest {

    private UserRegistrationValidator validator;

    @BeforeEach
    public void setupService() {
        validator = new UserRegistrationValidator();
    }

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
    public void givenValidEmails_WhenAnalyzed_ShouldAllPassSuccessfully(String validEmail) {
        assertDoesNotThrow(() -> {
            boolean result = validator.validateEmail(validEmail);
            assertTrue(result);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "abc",                  // Missing @
        "abc@.com.my",           // TLD component starts with dot
        "abc123@gmail.a",       // Terminal domain string too short
        "abc123@.com",          // Domain name missing
        "abc123@.com.com",      // Domain name missing
        ".abc@abc.com",         // Starts with structural point
        "abc()*@gmail.com",     // Prohibited character strings
        "abc@%*.com",           // Domain uses invalid symbols
        "abc..2002@gmail.com",  // Consecutive period symbols
        "abc.@gmail.com",       // Local extension section ends with dot
        "abc@abc@gmail.com",    // Redundant '@' assignment
        "abc@gmail.com.1a",     // Digits injected inside final TLD segment
        "abc@gmail.com.aa.au"   // Exceeds depth constraints
    })
    public void givenInvalidEmails_WhenAnalyzed_ShouldAllThrowCustomException(String invalidEmail) {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateEmail(invalidEmail);
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_EMAIL, exception.getType());
    }
}
