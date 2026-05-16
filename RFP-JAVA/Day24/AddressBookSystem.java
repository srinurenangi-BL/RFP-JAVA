package Day24;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookSystem {
    // UC 6: Dictionary of Address Book Name -> Address Book Engine
    private final Map<String, AddressBook> systemMap;

    public AddressBookSystem() {
        this.systemMap = new HashMap<>();
    }

    public void createNewAddressBook(String name) {
        systemMap.putIfAbsent(name, new AddressBook(name));
    }

    public AddressBook getAddressBook(String name) {
        return systemMap.get(name);
    }

    public Set<String> getBookNames() {
        return systemMap.keySet();
    }

    // Helper utility to flatten all nested profiles across books into a single collection
    private List<Contact> extractAllContacts() {
        return systemMap.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.toList());
    }

    // UC 8: Search Person in a City or State across ALL AddressBooks
    public List<Contact> searchByCityOrState(String targetLocation) {
        return extractAllContacts().stream()
                .filter(c -> c.getCity().equalsIgnoreCase(targetLocation) || c.getState().equalsIgnoreCase(targetLocation))
                .collect(Collectors.toList());
    }

    // UC 9: Maintain dynamic Dictionary views of City/State mapped to Persons
    public Map<String, List<Contact>> getGroupedByCity() {
        return extractAllContacts().stream().collect(Collectors.groupingBy(Contact::getCity));
    }

    public Map<String, List<Contact>> getGroupedByState() {
        return extractAllContacts().stream().collect(Collectors.groupingBy(Contact::getState));
    }

    // UC 10: Count of contact persons by City or State
    public long countByCity(String city) {
        return extractAllContacts().stream().filter(c -> c.getCity().equalsIgnoreCase(city)).count();
    }

    public long countByState(String state) {
        return extractAllContacts().stream().filter(c -> c.getState().equalsIgnoreCase(state)).count();
    }
}