package Day37;

import Day37.AddressBookInstance;
import Day37.Contact;
import Day37.AddressBookDataIO;
import Day37.JsonDataEngine;
import java.util.concurrent.CompletableFuture;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Welcome to Address Book Program");
        System.out.println("=================================================");

        AddressBookInstance defaultBook = new AddressBookInstance();

        // Instantiate sample contact
        Contact contact = new Contact();
        contact.setFirstName("John"); contact.setLastName("Doe");
        contact.setAddress("777 West Way"); contact.setCity("Miami");
        contact.setState("Florida"); contact.setZip("33101");
        contact.setPhoneNumber("9876543210");
        
        defaultBook.addContact(contact);

        // UC 15: Run non-blocking export processes across worker pipeline threads
        AddressBookDataIO jsonEngine = new JsonDataEngine();
        String jsonFilePath = "address_book_output.json";

        System.out.println("[Main App Framework] Forking off background async execution worker...");
        CompletableFuture<Boolean> asyncWriteTask = jsonEngine.writeDataAsync(defaultBook.getAllLocalMemoryContacts(), jsonFilePath);

        System.out.println("[Main App Framework] Continuing foreground compute frames immediately!");

        asyncWriteTask.thenAccept(success -> {
            if (success) {
                System.out.println("\n>>> [Async Worker Complete Notification] Data flushed to path: " + jsonFilePath);
            }
        });

        // Sleep to prevent JVM termination before the file write finishes
        try { Thread.sleep(1200); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}