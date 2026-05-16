package Day19;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class UserRegistration {

    // Regex Patterns for UC 1 to UC 4
    private static final String FIRST_NAME_REGEX = "^[A-Z][a-zA-Z]{2,}$";
    private static final String LAST_NAME_REGEX = "^[A-Z][a-zA-Z]{2,}$";
    private static final String MOBILE_REGEX = "^[0-9]{2}\\s[0-9]{10}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-zA-Z]{2})*$";

    // Master Password Regex combining UC 5, UC 6, UC 7, and UC 8
    // - (?=.*[A-Z]): At least 1 uppercase letter
    // - (?=.*[0-9]): At least 1 numeric digit
    // - (?=[^!@#$%^&*]*[!@#$%^&*][^!@#$%^&*]*$): EXACTLY 1 special character
    // - [A-Za-z0-9!@#$%^&*]{8,}: Minimum 8 characters total allowed
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=[^!@#$%^&*]*[!@#$%^&*][^!@#$%^&*]*$)[A-Za-z0-9!@#$%^&*]{8,}$";

    // Validation helper method
    public static boolean validateField(String input, String regex) {
        if (input == null) return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to User Registration System ===");

        // 1. First Name Validation (UC 1)
        System.out.print("\nEnter First Name (Starts with Cap, Min 3 chars): ");
        String firstName = scanner.nextLine();
        if (validateField(firstName, FIRST_NAME_REGEX)) {
            System.out.println("✅ Valid First Name");
        } else {
            System.out.println("❌ Invalid First Name. Must start with a Capital letter and have at least 3 characters.");
        }

        // 2. Last Name Validation (UC 2)
        System.out.print("\nEnter Last Name (Starts with Cap, Min 3 chars): ");
        String lastName = scanner.nextLine();
        if (validateField(lastName, LAST_NAME_REGEX)) {
            System.out.println("✅ Valid Last Name");
        } else {
            System.out.println("❌ Invalid Last Name. Must start with a Capital letter and have at least 3 characters.");
        }

        // 3. Email Validation (UC 3 / UC 9 Email Samples)
        System.out.print("\nEnter Email Address (e.g., abc.xyz@bl.co.in): ");
        String email = scanner.nextLine();
        if (validateField(email, EMAIL_REGEX)) {
            System.out.println("✅ Valid Email Address");
        } else {
            System.out.println("❌ Invalid Email Address format.");
        }

        // 4. Mobile Number Validation (UC 4)
        System.out.print("\nEnter Mobile Number (e.g., 91 9919819801): ");
        String mobile = scanner.nextLine();
        if (validateField(mobile, MOBILE_REGEX)) {
            System.out.println("✅ Valid Mobile Number");
        } else {
            System.out.println("❌ Invalid Format. Use Country Code followed by space and 10 digits.");
        }

        // 5. Password Validation (UC 5 - UC 8)
        System.out.print("\nEnter Password (Min 8 chars, 1+ Upper, 1+ Digit, Exactly 1 Special Char): ");
        String password = scanner.nextLine();
        if (validateField(password, PASSWORD_REGEX)) {
            System.out.println("✅ Valid Password");
        } else {
            System.out.println("❌ Invalid Password. Ensure it meets ALL security rules (Min 8 characters, 1 Upper, 1 Digit, and EXACTLY 1 special character).");
        }

        scanner.close();
        System.out.println("\n=== Registration Process Finished ===");
    }
}
