package Day22;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookSystem {
    // Dictionary mapping System Name -> AddressBook Engine
    private Map<String, AddressBook> addressBookMap;

    public AddressBookSystem() {
        this.addressBookMap = new HashMap<>();
    }

    public void addAddressBook(String name, AddressBook book) {
        addressBookMap.put(name, book);
    }

    // Helper to flatten all contacts across all managed address books into a single stream
    private List<Contact> getAllContacts() {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .collect(Collectors.toList());
    }

    // 🔍 UC 8: Ability to search Person in a City or State across multiple Address Books
    public List<Contact> searchPersonByCityOrState(String location) {
        return getAllContacts().stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(location) || contact.getState().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    // 🗺️ UC 9: Ability to view Persons by City or State (Returns grouped structural mapping)
    public Map<String, List<Contact>> viewPersonsByCity() {
        return getAllContacts().stream().collect(Collectors.groupingBy(Contact::getCity));
    }

    public Map<String, List<Contact>> viewPersonsByState() {
        return getAllContacts().stream().collect(Collectors.groupingBy(Contact::getState));
    }

    // 🔢 UC 10: Ability to get count of contact persons by City and State
    public long getCountByCity(String city) {
        return getAllContacts().stream().filter(c -> c.getCity().equalsIgnoreCase(city)).count();
    }

    public long getCountByState(String state) {
        return getAllContacts().stream().filter(c -> c.getState().equalsIgnoreCase(state)).count();
    }

    // 🔤 UC 11: Ability to sort the entries in the address book alphabetically by Person's Name
    public List<Contact> getSortedContactsByName() {
        return getAllContacts().stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                .collect(Collectors.toList());
    }

    // 🎯 UC 12: Ability to sort entries by City, State, or Zip
    public List<Contact> getSortedContactsByField(String criterion) {
        switch (criterion.toLowerCase()) {
            case "city":
                return getAllContacts().stream().sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
            case "state":
                return getAllContacts().stream().sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
            case "zip":
                return getAllContacts().stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
            default:
                return getSortedContactsByName();
        }
    }
}
