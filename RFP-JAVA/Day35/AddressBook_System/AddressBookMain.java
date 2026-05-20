package Day35.AddressBook_System;

import Day35.AddressBook_System.AddressBookInstance;
import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.AddressBookDataIO;
import Day35.AddressBook_System.JsonDataEngine;

import java.util.concurrent.CompletableFuture;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Welcome to Address Book Program");
        System.out.println("=================================================");

        AddressBookInstance activeBook = new AddressBookInstance();

        // Instantiate sample data
        Contact sample = new Contact();
        sample.setFirstName("Alice"); 
        sample.setLastName("Wonderland"); 
        sample.setAddress("123 Rabbit Hole");
        sample.setCity("Los Angeles"); 
        sample.setState("California"); 
        sample.setZip("90001");
        sample.setPhoneNumber("9991112223"); 
        sample.setEmail("alice@wonder.com");
        
        activeBook.addContact(sample);

        // Fire-and-forget non-blocking export path using the interface matching strategy
        AddressBookDataIO dataIOEngine = new JsonDataEngine();
        String filepath = "address_book_export.json";

        System.out.println("Main thread progress: Initializing Async I/O worker pipeline thread...");
        CompletableFuture<Boolean> asyncWriteResult = dataIOEngine.writeDataAsync(activeBook.getAllLocalMemoryContacts(), filepath);

        // This demonstrates multi-threading: the main thread keeps working immediately!
        System.out.println("Main thread progress: Continuing calculations down here without blocking UI or waiting for files.");

        asyncWriteResult.thenAccept(success -> {
            if (success) {
                System.out.println("\n[Async Callback Success Notice] Data completely exported to " + filepath);
            }
        });

        // Keeps JVM alive long enough to see the async output complete
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }
}