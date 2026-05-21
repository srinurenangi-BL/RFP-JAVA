export class Contact {
    // Strict Regex Validation Patterns (UC 1, UC 2)
    static NAME_REGEX = /^[A-Z][a-zA-Z]{2,}$/;                 // Capital start, min 3 chars
    static ADDRESS_REGEX = /^[a-zA-Z0-9\s,.-]{4,}$/;           // Min 4 chars
    static ZIP_REGEX = /^[0-9]{3}[ ]?[0-9]{3}$/;               // 6 Digits, optional middle space
    static PHONE_REGEX = /^[0-9]{10}$|^[+][0-9]{2}[ ]?[0-9]{10}$/; // 10 digits or with country code
    static EMAIL_REGEX = /^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-zA-Z]{2})?$/;

    constructor(firstName, lastName, address, city, state, zip, phoneNumber, email) {
        this.firstName = this._validate(firstName, Contact.NAME_REGEX, "First Name (Must start with Capital and have min 3 characters)");
        this.lastName = this._validate(lastName, Contact.NAME_REGEX, "Last Name (Must start with Capital and have min 3 characters)");
        this.address = this._validate(address, Contact.ADDRESS_REGEX, "Address (Must have min 4 characters)");
        this.city = this._validate(city, Contact.ADDRESS_REGEX, "City (Must have min 4 characters)");
        this.state = this._validate(state, Contact.ADDRESS_REGEX, "State (Must have min 4 characters)");
        this.zip = this._validate(zip, Contact.ZIP_REGEX, "Zip Code (Must be valid 6 digits)");
        this.phoneNumber = this._validate(phoneNumber, Contact.PHONE_REGEX, "Phone Number (Must be a valid 10-digit number)");
        this.email = this._validate(email, Contact.EMAIL_REGEX, "Email (Must be a valid email format)");
    }

    // Helper validation utility that throws an error on failure (UC 2)
    _validate(value, regex, fieldName) {
        if (!regex.test(value)) {
            throw new Error(`Validation Error: Invalid ${fieldName}. Provided value: "${value}"`);
        }
        return value;
    }

    // Overriding toString() for automated standard console logs (UC 11)
    toString() {
        return `Contact [Name: ${this.firstName} ${this.lastName}, Address: ${this.address}, City: ${this.city}, State: ${this.state}, Zip: ${this.zip}, Phone: ${this.phoneNumber}, Email: ${this.email}]`;
    }
}