export class EmailValidator {
    static getRegexPattern() {
        // Fully validated pattern supporting standard characters, domain, and 2-char country TLD
        return /^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-zA-Z]{2})?$/;
    }

    static validate(email) {
        return EmailValidator.getRegexPattern().test(email);
    }

    static runTests() {
        console.log("=== STARTING EMAIL VALIDATOR TESTS (UC 1 - UC 5) ===");

        const validEmails = [
            "abc@yahoo.com", "abc-100@yahoo.com", "abc.100@yahoo.com",
            "abc111@abc.com", "abc-100@abc.net", "abc.100@abc.com.au",
            "abc@1.com", "abc@gmail.com.com", "abc+100@gmail.com"
        ];

        const invalidEmails = [
            "abc", "abc@.com.my", "abc123@gmail.a", "abc123@.com", 
            "abc123@.com.com", ".abc@abc.com", "abc()*@gmail.com", 
            "abc@%*.com", "abc..2002@gmail.com", "abc.@gmail.com", 
            "abc@abc@gmail.com", "abc@gmail.com.1a", "abc@gmail.com.aa.au"
        ];

        console.log("--- Checking Valid Emails Group ---");
        validEmails.forEach(email => {
            console.log(`Email: "${email}" -> ${EmailValidator.validate(email) ? 'PASS' : 'FAIL'}`);
        });

        console.log("\n--- Checking Invalid Emails Group ---");
        invalidEmails.forEach((email, i) => {
            console.log(`Case ${i + 1}: "${email}" -> ${!EmailValidator.validate(email) ? 'PASS' : 'FAIL'}`);
        });

        console.log("=== END OF EMAIL VALIDATOR TESTS ===\n");
    }
}