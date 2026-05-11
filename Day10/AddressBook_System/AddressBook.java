package Day10.AddressBook_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    // UC 4: Use Collection Class to maintain multiple contact persons
    private List<Contact> contactsList;
    private Scanner scanner;

    public AddressBook() {
        this.contactsList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // UC 1 & UC 4: Ability to add one or multiple new Contacts
    public void addContacts() {
        boolean addMore = true;
        while (addMore) {
            System.out.println("\n--- Add New Contact ---");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("City: ");
            String city = scanner.nextLine();
            System.out.print("State: ");
            String state = scanner.nextLine();
            System.out.print("Zip Code: ");
            String zip = scanner.nextLine();
            System.out.print("Phone Number: ");
            String phone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phone, email);
            contactsList.add(newContact);
            System.out.println("Contact added successfully!");

            // UC 4: Add person details one at a time via console loop
            System.out.print("Do you want to add another contact? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                addMore = false;
            }
        }
    }

    // UC 2: Ability to edit existing contact using their name
    public void editContact() {
        System.out.print("\nEnter First Name of contact to edit: ");
        String editName = scanner.nextLine();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().equalsIgnoreCase(editName)) {
                System.out.println("Contact found. Enter new details (Press Enter to keep current):");
                
                System.out.print("New Phone Number [" + contact.getPhoneNumber() + "]: ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) contact.setPhoneNumber(phone);

                System.out.print("New City [" + contact.getCity() + "]: ");
                String city = scanner.nextLine();
                if (!city.isEmpty()) contact.setCity(city);

                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    // UC 3: Ability to delete a person using person's name
    public void deleteContact() {
        System.out.print("\nEnter First Name of contact to delete: ");
        String deleteName = scanner.nextLine();
        boolean removed = contactsList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(deleteName));

        if (removed) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void displayAllContacts() {
        if (contactsList.isEmpty()) {
            System.out.println("This Address Book is empty.");
        } else {
            for (Contact contact : contactsList) {
                System.out.println(contact.toString());
            }
        }
    }
}
