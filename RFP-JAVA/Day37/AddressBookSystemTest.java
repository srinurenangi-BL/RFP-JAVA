package Day37;

import Day37.AddressBookInstance;
import Day37.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookSystemTest {

    @Test
    public void givenDuplicateContact_WhenAddedToSystem_ShouldReturnFalseAndBlockRecord() {
        // Arrange
        AddressBookInstance book = new AddressBookInstance();
        Contact first = new Contact();
        first.setFirstName("John"); first.setLastName("Doe");
        
        Contact duplicate = new Contact();
        duplicate.setFirstName("John"); duplicate.setLastName("Doe");

        // Act
        boolean initialAddStatus = book.addContact(first);
        boolean duplicateAddStatus = book.addContact(duplicate);

        // Assert
        Assertions.assertTrue(initialAddStatus, "The first contact should be added successfully.");
        Assertions.assertFalse(duplicateAddStatus, "The duplicate contact should be rejected by the system.");
    }
}