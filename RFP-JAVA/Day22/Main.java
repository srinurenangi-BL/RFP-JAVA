package Day22;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AddressBookSystem system = new AddressBookSystem();

        // Create Address Books
        AddressBook personalBook = new AddressBook("Personal");
        AddressBook professionalBook = new AddressBook("Professional");

        // Create Sample Contacts
        Contact c1 = new Contact("Amit", "Sharma", "Mumbai", "Maharashtra", "400001", "9876543210", "amit@test.com");
        Contact c2 = new Contact("Rohit", "Verma", "Bangalore", "Karnataka", "560001", "8765432109", "rohit@test.com");
        Contact c3 = new Contact("Amit", "Sharma", "Mumbai", "Maharashtra", "400001", "9876543210", "dup@test.com"); // Duplicate
        Contact c4 = new Contact("Ananya", "Iyer", "Bangalore", "Karnataka", "560001", "7654321098", "ananya@test.com");

        // --- UC 7 Test: Duplicate Prevention ---
        System.out.println("--- Registering Contacts ---");
        personalBook.addContact(c1);
        personalBook.addContact(c2);
        personalBook.addContact(c3); // Will trigger explicit duplicate block notice
        professionalBook.addContact(c4);

        system.addAddressBook("Personal", personalBook);
        system.addAddressBook("Professional", professionalBook);

        // --- UC 8 Test: Cross-Book Search Engine ---
        System.out.println("\n--- Searching for Contacts in Bangalore ---");
        List<Contact> bangaloreContacts = system.searchPersonByCityOrState("Bangalore");
        bangaloreContacts.forEach(System.out::println);

        // --- UC 9 Test: View Grouping Mapping Data ---
        System.out.println("\n--- Viewing Grouped Mapping Profile by City ---");
        Map<String, List<Contact>> cityDictionary = system.viewPersonsByCity();
        cityDictionary.forEach((city, contacts) -> System.out.println(city + " -> " + contacts));

        // --- UC 10 Test: Group Count Aggregations ---
        System.out.println("\n--- Verification Statistics ---");
        System.out.println("Total Contacts in Mumbai: " + system.getCountByCity("Mumbai"));
        System.out.println("Total Contacts in Karnataka: " + system.getCountByState("Karnataka"));

        // --- UC 11 Test: Alphabetical Name Sort Output ---
        System.out.println("\n--- Alphabetical Name Order Results ---");
        system.getSortedContactsByName().forEach(contact -> System.out.println(contact));
    }
}
