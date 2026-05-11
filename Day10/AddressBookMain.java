package Day10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    // UC 5: Maintain Dictionary of Address Book Name to Address Book
    private Map<String, AddressBook> addressBookDictionary;
    private Scanner scanner;

    public AddressBookMain() {
        addressBookDictionary = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // UC 5: Add new Address Book
    public void addNewAddressBook() {
        System.out.print("\nEnter a unique name for the new Address Book: ");
        String bookName = scanner.nextLine();
        
        if (addressBookDictionary.containsKey(bookName)) {
            System.out.println("An Address Book with this name already exists!");
        } else {
            addressBookDictionary.put(bookName, new AddressBook());
            System.out.println("Address Book '" + bookName + "' created successfully.");
        }
    }

    public void accessAddressBook() {
        System.out.print("\nEnter the name of the Address Book you want to access: ");
        String bookName = scanner.nextLine();

        if (addressBookDictionary.containsKey(bookName)) {
            AddressBook currentBook = addressBookDictionary.get(bookName);
            manageAddressBook(currentBook, bookName);
        } else {
            System.out.println("Address Book not found!");
        }
    }

    private void manageAddressBook(AddressBook addressBook, String bookName) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Managing Address Book: " + bookName + " ---");
            System.out.println("1. Add Contact(s)");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addressBook.addContacts(); break;
                case 2: addressBook.editContact(); break;
                case 3: addressBook.deleteContact(); break;
                case 4: addressBook.displayAllContacts(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        // START Requirement: Welcome Message on Master Branch
        System.out.println("Welcome to Address Book Program");

        AddressBookMain systemMain = new AddressBookMain();
        Scanner mainScanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== SYSTEM MAIN MENU ===");
            System.out.println("1. Create New Address Book");
            System.out.println("2. Access Existing Address Book");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = mainScanner.nextInt();
            mainScanner.nextLine(); 

            switch (choice) {
                case 1: systemMain.addNewAddressBook(); break;
                case 2: systemMain.accessAddressBook(); break;
                case 3: 
                    exit = true; 
                    System.out.println("Exiting Address Book System...");
                    break;
                default: System.out.println("Invalid option.");
            }
        }
        mainScanner.close();
    }
}
