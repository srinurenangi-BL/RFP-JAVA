package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.JdbcDatabaseEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookSystemTest {

    @Test
    public void givenNewAddress_WhenUpdated_ShouldSyncWithDatabaseState() throws Exception {
        // Arrange
        JdbcDatabaseEngine databaseEngine = new JdbcDatabaseEngine();
        String searchFirst = "Alice";
        String searchLast = "Wonderland";
        String revisedAddress = "456 Mad Hatter Lane";

        // Act: Sync state to the DB via JDBC
        boolean updateExecuted = databaseEngine.updateContactAddress(searchFirst, searchLast, revisedAddress);
        List<Contact> memorySnapshot = databaseEngine.readDataAsync(null).get();
        
        Contact updatedContact = memorySnapshot.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(searchFirst) && c.getLastName().equalsIgnoreCase(searchLast))
                .findFirst()
                .orElse(null);

        // Assert: Ensure local application state matches the DB records (UC 17)
        Assertions.assertTrue(updateExecuted, "The database driver command failed to modify any database rows.");
        Assertions.assertNotNull(updatedContact, "The requested contact entity was missing from the verification list.");
        Assertions.assertEquals(revisedAddress, updatedContact.getAddress(), "Memory space data did not match the underlying storage state.");
    }
}