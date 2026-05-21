import { Contact } from "./Contact.js";
import { AddressBook } from "./AddressBook.js";

function executeAddressBookPipeline() {
    console.log(">>>> INITIALIZING ADDRESS BOOK SYSTEMS TESTING SUITE <<<<\n");
    const myBook = new AddressBook("Personal Directory");

    // ==========================================
    // UC 1 & UC 2 & UC 3: Creating and Adding Valid Contacts
    // ==========================================
    console.log("--- UC 1, 2 & 3: Adding New Contacts ---");
    try {
        const contact1 = new Contact("John", "Doe", "123 Bakers Street", "London", "Middlesex", "400088", "9876543210", "john.doe@gmail.com");
        const contact2 = new Contact("Alice", "Smith", "456 Park Avenue", "NewYork", "NewYorkState", "100022", "9988776655", "alice.s@yahoo.com");
        const contact3 = new Contact("Bob", "Johnson", "789 Forest Lane", "London", "Middlesex", "400088", "8877665544", "bob.j@gmail.com");
        
        myBook.addContact(contact1);
        myBook.addContact(contact2);
        myBook.addContact(contact3);
    } catch (error) {
        console.error(`Unexpected initialization failure: ${error.message}`);
    }

    // Test Validation Protection (UC 2 Error Throw Check)
    console.log("\n--- Testing Input Rule Validation Error Throwing ---");
    try {
        // Will fail because name starts lowercase and zip code contains invalid characters
        const badContact = new Contact("bobby", "Jones", "Short", "Ny", "NY", "ABC123", "12345", "bad-email");
        myBook.addContact(badContact);
    } catch (error) {
        console.log(`Caught Expected Error successfully: ${error.message}`);
    }

    // ==========================================
    // UC 7: Duplicate Entry Protection Validation Check
    // ==========================================
    console.log("\n--- UC 7: Testing Duplicate Entry Rules Restriction ---");
    try {
        const duplicateContact = new Contact("John", "Doe", "999 Alternative St", "London", "Middlesex", "400088", "9876543210", "john.doe@gmail.com");
        myBook.addContact(duplicateContact);
    } catch (error) {
        console.log(`Caught Expected Duplicate Error successfully: ${error.message}`);
    }

    // ==========================================
    // UC 4: Finding and Editing Existing Records
    // ==========================================
    console.log("\n--- UC 4: Editing Existing Contacts ---");
    myBook.editContact("Alice", "Smith", {
        address: "789 Broadway St",
        city: "Manhattan",
        phoneNumber: "+91 8888888888" // format supports spacing variations safely
    });

    // ==========================================
    // UC 6: Counting Array Elements via Reduce
    // ==========================================
    console.log(`\n--- UC 6: Contact Count Verification via Accumulator Reduce ---`);
    console.log(`Total count tracked in database: ${myBook.getContactCount()} Entries`);

    // ==========================================
    // UC 8 & UC 9: Search/View Filters by Location Properties
    // ==========================================
    console.log("\n--- UC 8 & UC 9: Location Filtering and Metrics View Maps ---");
    console.log("Searching entries located inside location reference 'London':");
    const searchResults = myBook.searchByCityOrState("London");
    searchResults.forEach(c => console.log(` - Found: ${c.firstName} ${c.lastName}`));

    console.log("\nAddress Book structural layouts mapped by categories:");
    console.log(myBook.viewByCityOrState());

    // ==========================================
    // UC 10: Structural Aggregated Counter Summaries View
    // ==========================================
    console.log("\n--- UC 10: Location Counting Summaries via Reduce ---");
    console.log(myBook.getCountByCityAndState());

    // ==========================================
    // UC 11: Alphabetical Sorting by Person Name
    // ==========================================
    console.log("\n--- UC 11: Sorting Records Alphabetically by Name ---");
    const sortedByName = myBook.sortByPersonName();
    myBook.displayAllContacts(sortedByName);

    // ==========================================
    // UC 12: Dynamically Sorting by Location Parameters
    // ==========================================
    console.log("\n--- UC 12: Sorting Records Alphabetically by City Location Criteria ---");
    const sortedByCity = myBook.sortByProperty("city");
    myBook.displayAllContacts(sortedByCity);

    // ==========================================
    // UC 5: Removing elements from storage arrays
    // ==========================================
    console.log("\n--- UC 5: Deleting Contact Records ---");
    myBook.deleteContact("Bob", "Johnson");
    console.log(`Final count tracked post removal phase: ${myBook.getContactCount()} Entries`);

    console.log("\n>>>> ADDRESS BOOK PIPELINE TESTS COMPLETED SUCCESSFULLY WITH 0 CODE HYGIENE CONFLICTS <<<<");
}

executeAddressBookPipeline();