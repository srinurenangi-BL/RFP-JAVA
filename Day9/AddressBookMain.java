package Day9;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        // Master Branch Requirement: Displaying Welcome Message
        System.out.println("Welcome to Address Book Program");

        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Address Book Menu ---");
            System.out.println("1. Add a New Contact");
            System.out.println("2. Edit an Existing Contact");
            System.out.println("3. Delete a Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    addressBook.addContact();
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.deleteContact();
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Address Book. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
        scanner.close();
    }
}
