package Day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailParameterizedTest {

    private UserRegistrationValidator validator;

    @BeforeEach
    public void setupService() {
        validator = new UserRegistrationValidator();
    }

    @Test
    public void givenValidEmails_WhenAnalyzed_ShouldAllPassSuccessfully() {
        String[] validEmails = {
            "abc@yahoo.com",
            "abc-100@yahoo.com",
            "abc.100@yahoo.com",
            "abc111@abc.com",
            "abc-100@abc.net",
            "abc.100@abc.com.au",
            "abc@1.com",
            "abc@gmail.com.com",
            "abc+100@gmail.com"
        };
        for (String validEmail : validEmails) {
            assertDoesNotThrow(() -> {
                boolean result = validator.validateEmail.validate(validEmail);
                assertTrue(result);
            });
        }
    }

    @Test
    public void givenInvalidEmails_WhenAnalyzed_ShouldAllThrowCustomException() {
        String[] invalidEmails = {
            "abc", "abc@.com.my", "abc123@gmail.a", "abc123@.com",
            "abc123@.com.com", ".abc@abc.com", "abc()*@gmail.com",
            "abc@%*.com", "abc..2002@gmail.com", "abc.@gmail.com",
            "abc@abc@gmail.com", "abc@gmail.com.1a", "abc@gmail.com.aa.au"
        };
        for (String invalidEmail : invalidEmails) {
            UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
                validator.validateEmail.validate(invalidEmail);
            });
            assertEquals(UserRegistrationException.ExceptionType.INVALID_EMAIL, exception.getType());
        }
    }
}
```</T>
