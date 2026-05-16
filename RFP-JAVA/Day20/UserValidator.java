package Day20;

import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,4})(\\.[a-zA-Z]{2})*$";

    public boolean validateEmail(String email) {
        if (email == null) return false;
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}