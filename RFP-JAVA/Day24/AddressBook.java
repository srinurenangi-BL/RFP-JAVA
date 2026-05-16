package Day24;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {
    private final String bookName;
    private final List<Contact> contacts;

    public AddressBook(String bookName) {
        this.bookName = bookName;
        this.contacts = new ArrayList<>();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    // UC 7: Check for duplicates using Java Streams before adding a entry
    public boolean addContact(Contact newContact) {
        boolean isDuplicate = contacts.stream().anyMatch(c -> c.equals(newContact));
        if (isDuplicate) {
            System.out.println("❌ Entry skipped: A contact named '" + newContact.getFirstName() + " " + newContact.getLastName() + "' already exists.");
            return false;
        }
        contacts.add(newContact);
        return true;
    }

    // UC 2: Locate and edit contact metrics using identity strings
    public boolean editContact(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email) {
        Optional<Contact> match = contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName))
                .findFirst();

        if (match.isPresent()) {
            Contact c = match.get();
            c.setAddress(address);
            c.setCity(city);
            c.setState(state);
            c.setZip(zip);
            c.setPhoneNumber(phone);
            c.setEmail(email);
            return true;
        }
        return false;
    }

    // UC 3: Delete entry cleanly via conditional predicates
    public boolean deleteContact(String firstName, String lastName) {
        return contacts.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName));
    }

    // UC 11 & UC 12: Sort operations using dynamic mapping criteria
    public List<Contact> getSortedEntries(String criterion) {
        switch (criterion.toLowerCase()) {
            case "name":
                return contacts.stream()
                        .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                        .collect(Collectors.toList());
            case "city":
                return contacts.stream().sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
            case "state":
                return contacts.stream().sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
            case "zip":
                return contacts.stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
            default:
                return contacts;
        }
    }
}
