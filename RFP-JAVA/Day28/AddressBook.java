package Day28;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private final List<ContactPerson> contactList;

    public AddressBook() {
        this.contactList = new ArrayList<>();
    }

    public List<ContactPerson> getContactList() {
        return contactList;
    }

    // UC 1 & UC 4 & UC 6: Add Contact with Stream-based Duplicate Check
    public boolean addContact(ContactPerson newContact) {
        boolean isDuplicate = contactList.stream().anyMatch(c -> c.equals(newContact));
        if (isDuplicate) {
            System.out.println("Error: A contact named " + newContact.getFirstName() + " " + newContact.getLastName() + " already exists in this address book.");
            return false;
        }
        contactList.add(newContact);
        return true;
    }

    // UC 2: Edit Contact
    public boolean editContact(String firstName, String lastName, ContactPerson updatedContact) {
        for (int i = 0; i < contactList.size(); i++) {
            ContactPerson c = contactList.get(i);
            if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)) {
                contactList.set(i, updatedContact);
                return true;
            }
        }
        return false;
    }

    // UC 3: Delete Contact using Stream/Collection methods
    public boolean deleteContact(String firstName, String lastName) {
        return contactList.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName));
    }

    // UC 10: Sort Alphabetically by Name using Streams
    public List<ContactPerson> sortByName() {
        return contactList.stream()
                .sorted(Comparator.comparing(ContactPerson::getFirstName).thenComparing(ContactPerson::getLastName))
                .collect(Collectors.toList());
    }

    // UC 11: Sort by City, State, or Zip using Streams
    public List<ContactPerson> sortByCity() {
        return contactList.stream().sorted(Comparator.comparing(ContactPerson::getCity)).collect(Collectors.toList());
    }

    public List<ContactPerson> sortByState() {
        return contactList.stream().sorted(Comparator.comparing(ContactPerson::getState)).collect(Collectors.toList());
    }

    public List<ContactPerson> sortByZip() {
        return contactList.stream().sorted(Comparator.comparing(ContactPerson::getZip)).collect(Collectors.toList());
    }

    public void displayContacts(List<ContactPerson> list) {
        if (list.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
