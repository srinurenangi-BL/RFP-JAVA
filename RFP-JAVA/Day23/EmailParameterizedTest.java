package Day23;

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
            boolean result = validator.validateEmail.validate(validEmail);
            assertTrue(result);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "abc", "abc@.com.my", "abc123@gmail.a", "abc123@.com", 
        "abc123@.com.com", ".abc@abc.com", "abc()*@gmail.com", 
        "abc@%*.com", "abc..2002@gmail.com", "abc.@gmail.com", 
        "abc@abc@gmail.com", "abc@gmail.com.1a", "abc@gmail.com.aa.au"
    })
    public void givenInvalidEmails_WhenAnalyzed_ShouldAllThrowCustomException(String invalidEmail) {
        UserRegistrationException exception = assertThrows(UserRegistrationException.class, () -> {
            validator.validateEmail.validate(invalidEmail);
        });
        assertEquals(UserRegistrationException.ExceptionType.INVALID_EMAIL, exception.getType());
    }
}
```</T>
