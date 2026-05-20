package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// UC 23 Open-Closed Principle compliance implementation mapping
public interface AddressBookDataIO {
    CompletableFuture<List<Contact>> readDataAsync(String destinationSource);
    CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String destinationSource);
}