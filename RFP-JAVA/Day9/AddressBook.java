package Day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private List<Contact> contactsList;
    private Scanner scanner;

    public AddressBook() {
        this.contactsList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // UC 2: Add a new Contact
    public void addContact() {
        System.out.println("\n--- Add New Contact ---");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Zip Code: ");
        String zip = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phone, email);
        contactsList.add(newContact);
        System.out.println("Contact added successfully!");
    }

    // UC 3: Edit existing contact using their name
    public void editContact() {
        System.out.print("\nEnter the First Name of the contact you want to edit: ");
        String editName = scanner.nextLine();
        boolean found = false;

        for (Contact contact : contactsList) {
            if (contact.getFirstName().equalsIgnoreCase(editName)) {
                found = true;
                System.out.println("Contact found. Enter new details (Leave blank to keep current):");

                System.out.print("Enter new Address [" + contact.getAddress() + "]: ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) contact.setAddress(address);

                System.out.print("Enter new City [" + contact.getCity() + "]: ");
                String city = scanner.nextLine();
                if (!city.isEmpty()) contact.setCity(city);

                System.out.print("Enter new State [" + contact.getState() + "]: ");
                String state = scanner.nextLine();
                if (!state.isEmpty()) contact.setState(state);

                System.out.print("Enter new Zip [" + contact.getZip() + "]: ");
                String zip = scanner.nextLine();
                if (!zip.isEmpty()) contact.setZip(zip);

                System.out.print("Enter new Phone [" + contact.getPhoneNumber() + "]: ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) contact.setPhoneNumber(phone);

                System.out.print("Enter new Email [" + contact.getEmail() + "]: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) contact.setEmail(email);

                System.out.println("Contact updated successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Contact with First Name '" + editName + "' not found.");
        }
    }

    // UC 4 (from prompt text): Delete a person using person's name
    public void deleteContact() {
        System.out.print("\nEnter the First Name of the contact you want to delete: ");
        String deleteName = scanner.nextLine();
        boolean removed = contactsList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(deleteName));

        if (removed) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact with First Name '" + deleteName + "' not found.");
        }
    }

    // Utility Method to display all contacts
    public void displayAllContacts() {
        if (contactsList.isEmpty()) {
            System.out.println("\nThe Address Book is currently empty.");
        } else {
            for (Contact contact : contactsList) {
                System.out.println(contact.toString());
            }
        }
    }
}
