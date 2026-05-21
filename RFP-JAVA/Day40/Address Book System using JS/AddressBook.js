import { Contact } from "./Contact.js";

export class AddressBook {
    constructor(name) {
        this.name = name;
        this.contactsArray = []; // UC 3: Initialize new array collection
    }

    // UC 3 & UC 7: Add Contact with Duplicate Prevention Validation Check
    addContact(contactInstance) {
        if (!(contactInstance instanceof Contact)) {
            throw new Error("Invalid input: Object must be an instance of the Contact class.");
        }

        // UC 7: Check duplicates via map and some
        const isDuplicate = this.contactsArray.some(c => 
            c.firstName.toLowerCase() === contactInstance.firstName.toLowerCase() &&
            c.lastName.toLowerCase() === contactInstance.lastName.toLowerCase()
        );

        if (isDuplicate) {
            throw new Error(`Operation Rejected: "${contactInstance.firstName} ${contactInstance.lastName}" already exists in this Address Book.`);
        }

        this.contactsArray.push(contactInstance);
        console.log(`Success: Added contact "${contactInstance.firstName} ${contactInstance.lastName}".`);
    }

    // UC 4: Find existing contact by name and update fields safely
    editContact(firstName, lastName, updatedFields = {}) {
        const contact = this.contactsArray.find(c => 
            c.firstName.toLowerCase() === firstName.toLowerCase() && 
            c.lastName.toLowerCase() === lastName.toLowerCase()
        );

        if (!contact) {
            console.log(`Edit Failed: Contact "${firstName} ${lastName}" not found.`);
            return false;
        }

        console.log(`\nEditing Contact "${firstName} ${lastName}"...`);
        // Instantiate updates through setters/constructor rules to re-trigger regex protections
        try {
            if (updatedFields.address) contact.address = contact._validate(updatedFields.address, Contact.ADDRESS_REGEX, "Address");
            if (updatedFields.city) contact.city = contact._validate(updatedFields.city, Contact.ADDRESS_REGEX, "City");
            if (updatedFields.state) contact.state = contact._validate(updatedFields.state, Contact.ADDRESS_REGEX, "State");
            if (updatedFields.zip) contact.zip = contact._validate(updatedFields.zip, Contact.ZIP_REGEX, "Zip");
            if (updatedFields.phoneNumber) contact.phoneNumber = contact._validate(updatedFields.phoneNumber, Contact.PHONE_REGEX, "Phone Number");
            if (updatedFields.email) contact.email = contact._validate(updatedFields.email, Contact.EMAIL_REGEX, "Email");
            
            console.log("Success: Contact updated successfully.");
            return true;
        } catch (error) {
            console.log(`Edit Aborted due to validation issue: ${error.message}`);
            return false;
        }
    }

    // UC 5: Find person by name and delete them from the array storage matrix
    deleteContact(firstName, lastName) {
        const initialLength = this.contactsArray.length;
        this.contactsArray = this.contactsArray.filter(c => 
            !(c.firstName.toLowerCase() === firstName.toLowerCase() && 
              c.lastName.toLowerCase() === lastName.toLowerCase())
        );

        if (this.contactsArray.length < initialLength) {
            console.log(`Success: Contact "${firstName} ${lastName}" has been removed.`);
            return true;
        }
        console.log(`Delete Failed: Contact "${firstName} ${lastName}" not found.`);
        return false;
    }

    // UC 6: Get total count of contacts using the array reduce accumulator function
    getContactCount() {
        return this.contactsArray.reduce((count) => count + 1, 0);
    }

    // UC 8: Search for matching contacts in a particular City or State
    searchByCityOrState(locationName) {
        const searchTarget = locationName.toLowerCase();
        return this.contactsArray.filter(c => 
            c.city.toLowerCase() === searchTarget || 
            c.state.toLowerCase() === searchTarget
        );
    }

    // UC 9: View matching contacts grouped explicitly by City or State names
    viewByCityOrState() {
        const viewMap = { cities: {}, states: {} };
        
        this.contactsArray.forEach(c => {
            if (!viewMap.cities[c.city]) viewMap.cities[c.city] = [];
            if (!viewMap.states[c.state]) viewMap.states[c.state] = [];
            
            viewMap.cities[c.city].push(`${c.firstName} ${c.lastName}`);
            viewMap.states[c.state].push(`${c.firstName} ${c.lastName}`);
        });
        return viewMap;
    }

    // UC 10: Get a dynamic aggregated count summaries sorted explicitly by location keys
    getCountByCityAndState() {
        return this.contactsArray.reduce((summary, c) => {
            summary.cityCount[c.city] = (summary.cityCount[c.city] || 0) + 1;
            summary.stateCount[c.state] = (summary.stateCount[c.state] || 0) + 1;
            return summary;
        }, { cityCount: {}, stateCount: {} });
    }

    // UC 11: Sort the entire Address Book entries alphabetically by Person's Name
    sortByPersonName() {
        return [...this.contactsArray].sort((a, b) => {
            const nameA = `${a.firstName} ${a.lastName}`.toLowerCase();
            const nameB = `${b.firstName} ${b.lastName}`.toLowerCase();
            return nameA.localeCompare(nameB);
        });
    }

    // UC 12: Sort the entire Address Book entries dynamically by City, State, or Zip Code
    sortByProperty(propertyKey) {
        const validKeys = ['city', 'state', 'zip'];
        if (!validKeys.includes(propertyKey.toLowerCase())) {
            throw new Error(`Sorting Error: Invalid key field pattern identifier "${propertyKey}". Select city, state, or zip.`);
        }
        
        return [...this.contactsArray].sort((a, b) => {
            const valA = a[propertyKey].toLowerCase();
            const valB = b[propertyKey].toLowerCase();
            return valA.localeCompare(valB);
        });
    }

    // Utility diagnostic helper to print out current array states using overwritten string maps
    displayAllContacts(arraySource = this.contactsArray) {
        if (arraySource.length === 0) {
            console.log("Address Book is empty.");
            return;
        }
        arraySource.forEach(c => console.log(c.toString()));
    }
}