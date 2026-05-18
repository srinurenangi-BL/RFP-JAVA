package Day28;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AddressBookSystem system = new AddressBookSystem();

    public static void main(String[] args) {
        // Master Branch Initialization Message
        System.out.println("=========================================");
        System.out.println("  Welcome to Address Book Program        ");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN SYSTEM MENU ---");
            System.out.println("1. Manage Address Books");
            System.out.println("2. Global Search (City/State)");
            System.out.println("3. View Persons Grouped by City/State");
            System.out.println("4. Get Contact Count by City/State");
            System.out.println("5. Exit");
            System.print("Choose an option: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> manageAddressBooks();
                case 2 -> globalSearch();
                case 3 -> viewGroupedContacts();
                case 4 -> getCounts();
                case 5 -> {
                    running = false;
                    System.out.println("Exiting Address Book System. Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void manageAddressBooks() {
        System.out.print("Enter Address Book Name to access/create: ");
        String bookName = scanner.nextLine().trim();
        if (bookName.isEmpty()) return;

        if (system.addAddressBook(bookName)) {
            System.out.println("Created new Address Book: " + bookName);
        }
        AddressBook currentBook = system.getAddressBook(bookName);
        
        boolean insideBook = true;
        while (insideBook) {
            System.out.println("\n--- Address Book: [" + bookName + "] ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. List Contacts (Default)");
            System.out.println("5. List Contacts Sorted Alphabetically by Name");
            System.out.println("6. List Contacts Sorted by City/State/Zip");
            System.out.println("7. Back to Main Menu");
            System.print("Choose an option: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> {
                    ContactPerson contact = inputContactDetails();
                    if (currentBook.addContact(contact)) {
                        System.out.println("Contact added successfully.");
                    }
                }
                case 2 -> {
                    System.print("Enter First Name to edit: ");
                    String fn = scanner.nextLine();
                    System.print("Enter Last Name to edit: ");
                    String ln = scanner.nextLine();
                    System.out.println("Enter updated details:");
                    ContactPerson updated = inputContactDetails();
                    if (currentBook.editContact(fn, ln, updated)) {
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                }
                case 3 -> {
                    System.print("Enter First Name to delete: ");
                    String dfn = scanner.nextLine();
                    System.print("Enter Last Name to delete: ");
                    String dln = scanner.nextLine();
                    if (currentBook.deleteContact(dfn, dln)) {
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                }
                case 4 -> currentBook.displayContacts(currentBook.getContactList());
                case 5 -> currentBook.displayContacts(currentBook.sortByName());
                case 6 -> sortSubMenu(currentBook);
                case 7 -> insideBook = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void sortSubMenu(AddressBook book) {
        System.out.println("Sort by: 1. City | 2. State | 3. Zip");
        int sortChoice = readInt();
        switch (sortChoice) {
            case 1 -> book.displayContacts(book.sortByCity());
            case 2 -> book.displayContacts(book.sortByState());
            case 3 -> book.displayContacts(book.sortByZip());
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void globalSearch() {
        System.out.println("Search by: 1. City | 2. State");
        int option = readInt();
        if (option == 1) {
            System.print("Enter City Name: ");
            String city = scanner.nextLine();
            system.searchByCity(city).forEach(System.out::println);
        } else if (option == 2) {
            System.print("Enter State Name: ");
            String state = scanner.nextLine();
            system.searchByState(state).forEach(System.out::println);
        }
    }

    private static void viewGroupedContacts() {
        System.out.println("Group by: 1. City | 2. State");
        int option = readInt();
        if (option == 1) {
            Map<String, List<ContactPerson>> map = system.getCityToPersonsMap();
            map.forEach((city, list) -> {
                System.out.println("\nCity: " + city.toUpperCase());
                list.forEach(c -> System.out.println("  " + c));
            });
        } else if (option == 2) {
            Map<String, List<ContactPerson>> map = system.getStateToPersonsMap();
            map.forEach((state, list) -> {
                System.out.println("\nState: " + state.toUpperCase());
                list.forEach(c -> System.out.println("  " + c));
            });
        }
    }

    private static void getCounts() {
        System.out.println("Count by: 1. City | 2. State");
        int option = readInt();
        if (option == 1) {
            System.print("Enter City Name: ");
            String city = scanner.nextLine();
            System.out.println("Total contacts in " + city + ": " + system.getCountByCity(city));
        } else if (option == 2) {
            System.print("Enter State Name: ");
            String state = scanner.nextLine();
            System.out.println("Total contacts in " + state + ": " + system.getCountByState(state));
        }
    }

    private static ContactPerson inputContactDetails() {
        System.print("First Name: "); String fn = scanner.nextLine();
        System.print("Last Name: "); String ln = scanner.nextLine();
        System.print("Address: "); String addr = scanner.nextLine();
        System.print("City: "); String city = scanner.nextLine();
        System.print("State: "); String state = scanner.nextLine();
        System.print("Zip: "); String zip = scanner.nextLine();
        System.print("Phone Number: "); String phone = scanner.nextLine();
        System.print("Email: "); String email = scanner.nextLine();
        return new ContactPerson(fn, ln, addr, city, state, zip, phone, email);
    }

    private static int readInt() {
        try {
            int val = Integer.parseInt(scanner.nextLine());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
