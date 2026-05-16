package Day22;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private String bookName;
    private List<Contact> contactList;

    public AddressBook(String bookName) {
        this.bookName = bookName;
        this.contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    // UC 7: Ensure there is no Duplicate Entry of the same person in a particular Address Book
    public boolean addContact(Contact newContact) {
        boolean isDuplicate = contactList.stream().anyMatch(contact -> contact.equals(newContact));
        if (isDuplicate) {
            System.out.println("❌ Duplicate entry found! " + newContact.getFirstName() + " already exists in " + bookName);
            return false;
        }
        contactList.add(newContact);
        return true;
    }
}
