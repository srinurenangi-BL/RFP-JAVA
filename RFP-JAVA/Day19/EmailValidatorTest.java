package Day19;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorTest {

    /**
     * Highly precise Email Regex Pattern:
     * - ^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)* : Cannot start/end with a dot, no consecutive dots.
     * - @[a-zA-Z0-9]+                      : Restricts domain name to letters/digits (no symbols).
     * - \.[a-zA-Z]{2,4}                    : First TLD layer must be 2-4 pure alphabetic characters (e.g., .com, .net).
     * - (\.[a-zA-Z]{2})*$                  : Optional second TLD layer, exactly 2 pure alphabetic characters (e.g., .au, .my).
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,4})(\\.[a-zA-Z]{2})*$";

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // --- 1. SAMPLE VALID EMAILS TEST ---
        String[] validEmails = {
            "abc@yahoo.com",
            "abc-100@yahoo.com",
            "abc.100@yahoo.com",
            "abc111@abc.com",
            "abc-100@abc.net",
            "abc.100@abc.com.au",
            "abc@1.com",
            "abc@gmail.com.com", // Valid according to structure (double 3-letter TLD handled as primary + secondary)
            "abc+100@gmail.com"
        };

        // --- 2. SAMPLE INVALID EMAILS TEST ---
        String[] invalidEmails = {
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
            "abc@gmail.com.aa.au"   // Cannot exceed maximum specified TLD depth layers
        };

        System.out.println("====== 🧪 RUNNING EMAIL VALIDATION TESTS ======\n");

        int validPassed = 0;
        System.out.println("--- Testing Expected VALID Emails ---");
        for (String email : validEmails) {
            boolean result = isValidEmail(email);
            if (result) {
                System.out.println("✅ [PASS] Validated correctly: " + email);
                validPassed++;
            } else {
                System.out.println("❌ [FAIL] Expected VALID but marked INVALID: " + email);
            }
        }

        int invalidPassed = 0;
        System.out.println("\n--- Testing Expected INVALID Emails ---");
        for (String email : invalidEmails) {
            boolean result = isValidEmail(email);
            if (!result) {
                System.out.println("✅ [PASS] Successfully caught invalid email: " + email);
                invalidPassed++;
            } else {
                System.out.println("❌ [FAIL] Expected INVALID but marked VALID: " + email);
            }
        }

        // --- FINAL SUMMARY ---
        System.out.println("\n================ SUMMARY ================");
        System.out.println("Valid Emails Passed:   " + validPassed + " / " + validEmails.length);
        System.out.println("Invalid Emails Caught: " + invalidPassed + " / " + invalidEmails.length);
        
        if (validPassed == validEmails.length && invalidPassed == invalidEmails.length) {
            System.out.println("\n🎉 SUCCESS: All your sample test criteria passed perfectly!");
        } else {
            System.out.println("\n⚠️ WARNING: Some test conditions failed.");
        }
    }
}