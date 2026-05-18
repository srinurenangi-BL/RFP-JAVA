package Day28;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookSystem {
    // UC 5: Dictionary of Address Book Name to Address Book
    private final Map<String, AddressBook> addressBookMap;

    public AddressBookSystem() {
        this.addressBookMap = new HashMap<>();
    }

    public boolean addAddressBook(String name) {
        if (addressBookMap.containsKey(name.toLowerCase())) {
            return false;
        }
        addressBookMap.put(name.toLowerCase(), new AddressBook());
        return true;
    }

    public AddressBook getAddressBook(String name) {
        return addressBookMap.get(name.toLowerCase());
    }

    public Set<String> getBookNames() {
        return addressBookMap.keySet();
    }

    // UC 7: Search Person in a City or State across ALL Address Books
    public List<ContactPerson> searchByCity(String city) {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<ContactPerson> searchByState(String state) {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    // UC 8: View Persons grouped by City or State (Dictionary of City/State to Persons)
    public Map<String, List<ContactPerson>> getCityToPersonsMap() {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .collect(Collectors.groupingBy(contact -> contact.getCity().toLowerCase()));
    }

    public Map<String, List<ContactPerson>> getStateToPersonsMap() {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .collect(Collectors.groupingBy(contact -> contact.getState().toLowerCase()));
    }

    // UC 9: Count contact persons by City or State
    public long getCountByCity(String city) {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .count();
    }

    public long getCountByState(String state) {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .count();
    }
}