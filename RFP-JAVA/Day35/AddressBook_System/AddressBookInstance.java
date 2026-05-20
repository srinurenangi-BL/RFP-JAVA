package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBookInstance {
    private final List<Contact> contactList = new ArrayList<>();

    // UC 2 & UC 7: Enforce uniqueness before appending elements via streams
    public boolean addContact(Contact contact) {
        if (contactList.stream().anyMatch(c -> c.equals(contact))) {
            System.out.println("System Alert: Prevented addition of duplicate contact name.");
            return false;
        }
        return contactList.add(contact);
    }

    // UC 3: Edit person using their name
    public boolean editContact(String first, String last, Contact updatedContact) {
        for (int i = 0; i < contactList.size(); i++) {
            Contact c = contactList.get(i);
            if (c.getFirstName().equalsIgnoreCase(first) && c.getLastName().equalsIgnoreCase(last)) {
                contactList.set(i, updatedContact);
                return true;
            }
        }
        return false;
    }

    // UC 4: Delete a person using their name
    public boolean deleteContact(String first, String last) {
        return contactList.removeIf(c -> c.getFirstName().equalsIgnoreCase(first) && c.getLastName().equalsIgnoreCase(last));
    }

    public List<Contact> getAllLocalMemoryContacts() { 
        return new ArrayList<>(contactList); 
    }

    // UC 8: Search person in a City or State across multiple books via lambdas
    public List<Contact> searchByCity(String city) {
        return contactList.stream().filter(c -> c.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
    }

    public List<Contact> searchByState(String state) {
        return contactList.stream().filter(c -> c.getState().equalsIgnoreCase(state)).collect(Collectors.toList());
    }

    // UC 10: Count persons by City or State
    public long getCountByCity(String city) {
        return contactList.stream().filter(c -> c.getCity().equalsIgnoreCase(city)).count();
    }

    public long getCountByState(String state) {
        return contactList.stream().filter(c -> c.getState().equalsIgnoreCase(state)).count();
    }

    // UC 11: Alphabetical sorting mechanics
    public List<Contact> getSortedByName() {
        return contactList.stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                .collect(Collectors.toList());
    }

    // UC 12: Sort by operational parameters
    public List<Contact> getSortedByCity() {
        return contactList.stream().sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
    }

    public List<Contact> getSortedByState() {
        return contactList.stream().sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
    }

    public List<Contact> getSortedByZip() {
        return contactList.stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
    }
}