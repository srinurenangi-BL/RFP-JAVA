package Day27.AddressBook_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {
    private String bookName;
    private List<Contact> contactList;

    public AddressBook(String bookName) {
        this.bookName = bookName;
        this.contactList = new ArrayList<>();
    }

    public String getBookName() { return bookName; }
    public List<Contact> getContactList() { return contactList; }
    public void setContactList(List<Contact> contactList) { this.contactList = contactList; }

    // UC 2 & UC 7: Add Contact with Duplicate Stream Check
    public void addContact(Scanner sc) {
        System.out.print("Enter First Name: ");
        String fName = sc.next();
        System.out.print("Enter Last Name: ");
        String lName = sc.next();

        // UC 7: Stream Check for duplicates
        boolean isDuplicate = contactList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase(fName) && c.getLastName().equalsIgnoreCase(lName));

        if (isDuplicate) {
            System.out.println("❌ Critical: A contact with this name already exists in this Address Book!");
            return;
        }

        System.out.print("Enter Address: ");
        sc.nextLine(); // Clear buffer
        String address = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.next();
        System.out.print("Enter State: ");
        String state = sc.next();
        System.out.print("Enter Zip Code: ");
        String zip = sc.next();
        System.out.print("Enter Phone Number: ");
        String phone = sc.next();
        System.out.print("Enter Email: ");
        String email = sc.next();

        Contact newContact = new Contact(fName, lName, address, city, state, zip, phone, email);
        contactList.add(newContact);
        System.out.println("✅ Contact added successfully!");
    }

    // UC 3: Edit Contact
    public void editContact(String fName, String lName, Scanner sc) {
        Contact contact = contactList.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(fName) && c.getLastName().equalsIgnoreCase(lName))
                .findFirst()
                .orElse(null);

        if (contact == null) {
            System.out.println("❌ Contact not found.");
            return;
        }

        System.out.print("Enter New Address: ");
        sc.nextLine();
        contact.setAddress(sc.nextLine());
        System.out.print("Enter New City: ");
        contact.setCity(sc.next());
        System.out.print("Enter New State: ");
        contact.setState(sc.next());
        System.out.print("Enter New Zip Code: ");
        contact.setZip(sc.next());
        System.out.print("Enter New Phone: ");
        contact.setPhoneNumber(sc.next());
        System.out.print("Enter New Email: ");
        contact.setEmail(sc.next());
        System.out.println("✏️ Contact updated successfully!");
    }

    // UC 4: Delete Contact
    public void deleteContact(String fName, String lName) {
        boolean removed = contactList.removeIf(c -> c.getFirstName().equalsIgnoreCase(fName) && c.getLastName().equalsIgnoreCase(lName));
        if (removed) {
            System.out.println("🗑️ Contact deleted successfully.");
        } else {
            System.out.println("❌ Contact not found.");
        }
    }

    // UC 11: Sort Alphabetically by Name using Streams
    public void sortByName() {
        List<Contact> sorted = contactList.stream()
                .sorted((c1, c2) -> (c1.getFirstName() + c1.getLastName()).compareToIgnoreCase(c2.getFirstName() + c2.getLastName()))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    // UC 12: Sort by City, State, or Zip Code using Streams
    public void sortByParam(String parameter) {
        List<Contact> sorted = new ArrayList<>();
        switch (parameter.toLowerCase()) {
            case "city" -> sorted = contactList.stream().sorted((c1, c2) -> c1.getCity().compareToIgnoreCase(c2.getCity())).collect(Collectors.toList());
            case "state" -> sorted = contactList.stream().sorted((c1, c2) -> c1.getState().compareToIgnoreCase(c2.getState())).collect(Collectors.toList());
            case "zip" -> sorted = contactList.stream().sorted((c1, c2) -> c1.getZip().compareTo(c2.getZip())).collect(Collectors.toList());
            default -> System.out.println("Invalid sorting parameter chosen.");
        }
        sorted.forEach(System.out::println);
    }

    public void displayAll() {
        if (contactList.isEmpty()) {
            System.out.println("The Address Book is empty.");
        } else {
            contactList.forEach(System.out::println);
        }
    }
}
