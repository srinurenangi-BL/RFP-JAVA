package Day37;

import Day37.Contact;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookInstance {
    private final List<Contact> memoryStoreList = new ArrayList<>();

    // UC 2 & UC 6: Add unique contacts using Stream API filters
    public boolean addContact(Contact contact) {
        if (memoryStoreList.stream().anyMatch(c -> c.equals(contact))) {
            System.err.println("Operation Blocked: Duplicate name record detected.");
            return false;
        }
        return memoryStoreList.add(contact);
    }

    // UC 3: Edit existing contact data by matching names
    public boolean editContact(String first, String last, Contact updated) {
        return memoryStoreList.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(first) && c.getLastName().equalsIgnoreCase(last))
                .findFirst()
                .map(c -> {
                    c.setAddress(updated.getAddress());
                    c.setCity(updated.getCity());
                    c.setState(updated.getState());
                    c.setZip(updated.getZip());
                    c.setPhoneNumber(updated.getPhoneNumber());
                    return true;
                }).orElse(false);
    }

    // UC 4: Delete contact person
    public boolean deleteContact(String first, String last) {
        return memoryStoreList.removeIf(c -> c.getFirstName().equalsIgnoreCase(first) && c.getLastName().equalsIgnoreCase(last));
    }

    public List<Contact> getAllLocalMemoryContacts() { return new ArrayList<>(memoryStoreList); }

    // UC 7: Alphabetical Sorting by name
    public List<Contact> getSortedByName() {
        return memoryStoreList.stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                .collect(Collectors.toList());
    }

    // UC 8: Sort entries by City, State, or Zip
    public List<Contact> getSortedByCity() { return memoryStoreList.stream().sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList()); }
    public List<Contact> getSortedByState() { return memoryStoreList.stream().sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList()); }
    public List<Contact> getSortedByZip() { return memoryStoreList.stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList()); }

    // UC 9 & 10: Filtering & Searching via Streams
    public List<Contact> searchByCity(String city) {
        return memoryStoreList.stream().filter(c -> c.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
    }
}