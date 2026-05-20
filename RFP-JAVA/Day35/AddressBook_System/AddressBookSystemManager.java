package Day35.AddressBook_System;

import java.util.HashMap;
import java.util.Map;

public class AddressBookSystemManager {
    // UC 6: Maintain dictionary mapping unique book name identifier strings to unique instances
    public final Map<String, AddressBookInstance> globalBooksIndex = new HashMap<>();

    public void addAddressBook(String bookName) {
        globalBooksIndex.putIfAbsent(bookName, new AddressBookInstance());
    }
}