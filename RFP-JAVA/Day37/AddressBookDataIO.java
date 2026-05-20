package Day37;

import Day37.Contact;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AddressBookDataIO {
    CompletableFuture<List<Contact>> readDataAsync(String pathSource);
    CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String pathSource);
}