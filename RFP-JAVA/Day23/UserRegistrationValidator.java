package Day23;

import java.util.regex.Pattern;

public class UserRegistrationValidator {

    // Regex Constants (UC 1 - UC 8)
    private static final String FIRST_NAME_REGEX = "^[A-Z][a-zA-Z]{2,}$";
    private static final String LAST_NAME_REGEX = "^[A-Z][a-zA-Z]{2,}$";
    private static final String MOBILE_REGEX = "^[0-9]{2}\\s[0-9]{10}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,4})(\\.[a-zA-Z]{2})*$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=[^!@#$%^&*]*[!@#$%^&*][^!@#$%^&*]*$)[A-Za-z0-9!@#$%^&*]{8,}$";

    // UC 13: Lambda Function for First Name Validation
    public final UserValidationFunction validateFirstName = (firstName) -> {
        if (firstName != null && Pattern.matches(FIRST_NAME_REGEX, firstName)) {
            return true;
        }
        throw new UserRegistrationException(
            UserRegistrationException.ExceptionType.INVALID_FIRST_NAME, 
            "First name must start with a capital letter and possess at least 3 characters."
        );
    };

    // UC 13: Lambda Function for Last Name Validation
    public final UserValidationFunction validateLastName = (lastName) -> {
        if (lastName != null && Pattern.matches(LAST_NAME_REGEX, lastName)) {
            return true;
        }
        throw new UserRegistrationException(
            UserRegistrationException.ExceptionType.INVALID_LAST_NAME, 
            "Last name must start with a capital letter and possess at least 3 characters."
        );
    };

    // UC 13: Lambda Function for Email Validation
    public final UserValidationFunction validateEmail = (email) -> {
        if (email != null && Pattern.matches(EMAIL_REGEX, email)) {
            return true;
        }
        throw new UserRegistrationException(
            UserRegistrationException.ExceptionType.INVALID_EMAIL, 
            "Provided email string layout is syntactically invalid."
        );
    };

    // UC 13: Lambda Function for Mobile Validation
    public final UserValidationFunction validateMobile = (mobile) -> {
        if (mobile != null && Pattern.matches(MOBILE_REGEX, mobile)) {
            return true;
        }
        throw new UserRegistrationException(
            UserRegistrationException.ExceptionType.INVALID_MOBILE, 
            "Mobile entry must conform to format: [Country Code][Space][10-Digit Identifier]."
        );
    };

    // UC 13: Lambda Function for Password Validation
    public final UserValidationFunction validatePassword = (password) -> {
        if (password != null && Pattern.matches(PASSWORD_REGEX, password)) {
            return true;
        }
        throw new UserRegistrationException(
            UserRegistrationException.ExceptionType.INVALID_PASSWORD, 
            "Password requirements violated: Min 8 chars, 1+ Upper, 1+ Digit, and EXACTLY 1 special character."
        );
    };
}
