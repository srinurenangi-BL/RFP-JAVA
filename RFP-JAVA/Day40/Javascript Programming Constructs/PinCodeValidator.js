export class PinCodeValidator {
    static getRegexPattern() {
        // Validates a 6-digit number with an optional middle single space
        return /^[0-9]{3}[ ]?[0-9]{3}$/;
    }

    static validate(pinCode) {
        return PinCodeValidator.getRegexPattern().test(pinCode);
    }

    static runTests() {
        console.log("=== STARTING PIN CODE VALIDATOR TESTS (UC 1 - UC 4) ===");
        
        const testCases = [
            { pin: "400088", expected: true, uc: "UC1: Valid PIN" },
            { pin: "A400088", expected: false, uc: "UC2: Restrict Alphabet at Start" },
            { pin: "400088B", expected: false, uc: "UC3: Restrict Alphabet at End" },
            { pin: "400 088", expected: true, uc: "UC4: Optional Middle Space" }
        ];

        testCases.forEach(tc => {
            const result = PinCodeValidator.validate(tc.pin);
            console.log(`[${tc.uc}] PIN: "${tc.pin}" -> ${result === tc.expected ? 'PASS' : 'FAIL'}`);
        });
        
        console.log("=== END OF PIN CODE VALIDATOR TESTS ===\n");
    }
}
