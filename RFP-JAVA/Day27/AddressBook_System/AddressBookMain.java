package Day27.AddressBook_System;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    private static final Map<String, AddressBook> addressBookMap = AddressBookFileIO.readData(); // Read on start [UC 13]

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  Welcome to Address Book Program in Master Branch "); // START Requirement [UC 1]
        System.out.println("=================================================");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SYSTEM DIRECTORY MENU ---");
            System.out.println("1. Access / Create Address Book");
            System.out.println("2. Global Contact Search across System (by City/State) [UC 8]");
            System.out.println("3. View Person Collections grouped (by City/State) [UC 9]");
            System.out.println("4. Dynamic Count Summary Metrics [UC 10]");
            System.out.println("5. Exit and Flush Cache to File IO Storage [UC 13]");
            System.out.print("Select execution path number: ");
            int rootChoice = sc.nextInt();

            switch (rootChoice) {
                case 1 -> handleAddressBookSubMenu(sc);
                case 2 -> globalSearch(sc);
                case 3 -> viewByCityOrState(sc);
                case 4 -> countByCityOrState(sc);
                case 5 -> {
                    AddressBookFileIO.writeData(addressBookMap); // Final File Save
                    System.out.println("Exiting application. Thank you!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option selection.");
            }
        }
    }

    private static void handleAddressBookSubMenu(Scanner sc) {
        System.out.print("Enter the name of the Address Book to work in: ");
        String name = sc.next();
        
        // UC 6: Maintain Dictionary of Name to Address Book
        AddressBook currentBook = addressBookMap.computeIfAbsent(name, AddressBook::new);

        while (true) {
            System.out.println("\n--- Managing Address Book: " + name + " ---");
            System.out.println("1. Add Contact [UC 2/5]");
            System.out.println("2. Edit Contact [UC 3]");
            System.out.println("3. Delete Contact [UC 4]");
            System.out.println("4. List All Entries Raw");
            System.out.println("5. Sort Entries Alphabetically [UC 11]");
            System.out.println("6. Sort Entries by City/State/Zip [UC 12]");
            System.out.println("7. Back to System Menu");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> currentBook.addContact(sc);
                case 2 -> {
                    System.out.print("First Name to edit: "); String fn = sc.next();
                    System.out.print("Last Name to edit: "); String ln = sc.next();
                    currentBook.editContact(fn, ln, sc);
                }
                case 3 -> {
                    System.out.print("First Name to delete: "); String fn = sc.next();
                    System.out.print("Last Name to delete: "); String ln = sc.next();
                    currentBook.deleteContact(fn, ln);
                }
                case 4 -> currentBook.displayAll();
                case 5 -> currentBook.sortByName();
                case 6 -> {
                    System.out.print("Enter parameters to sort by (city / state / zip): ");
                    currentBook.sortByParam(sc.next());
                }
                case 7 -> { return; }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    // UC 8: Search Person in City or State across multiple Address Books via Streams
    private static void globalSearch(Scanner sc) {
        System.out.print("Enter First Name to target: ");
        String targetName = sc.next();
        System.out.print("Search inside 'city' or 'state' parameters? : ");
        String scope = sc.next();
        System.out.print("Enter specific Region Value: ");
        String region = sc.next();

        addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .filter(c -> c.getFirstName().equalsIgnoreCase(targetName) &&
                        (scope.equalsIgnoreCase("city") ? c.getCity().equalsIgnoreCase(region) : c.getState().equalsIgnoreCase(region)))
                .forEach(System.out::println);
    }

    // UC 9: View Person lists mapped to targeted criteria values
    private static void viewByCityOrState(Scanner sc) {
        System.out.print("Group contact records by (city / state): ");
        String selection = sc.next();

        if (selection.equalsIgnoreCase("city")) {
            Map<String, List<Contact>> cityDict = addressBookMap.values().stream()
                    .flatMap(b -> b.getContactList().stream())
                    .collect(Collectors.groupingBy(Contact::getCity));
            cityDict.forEach((city, contacts) -> System.out.println("🏙️ City: " + city + " -> " + contacts));
        } else {
            Map<String, List<Contact>> stateDict = addressBookMap.values().stream()
                    .flatMap(b -> b.getContactList().stream())
                    .collect(Collectors.groupingBy(Contact::getState));
            stateDict.forEach((state, contacts) -> System.out.println("🗺️ State: " + state + " -> " + contacts));
        }
    }

    // UC 10: Dynamic Count analysis using Streams pipeline termination rules
    private static void countByCityOrState(Scanner sc) {
        System.out.print("Count aggregation scope metric (city / state): ");
        String selection = sc.next();

        Map<String, Long> countMap = addressBookMap.values().stream()
                .flatMap(b -> b.getContactList().stream())
                .collect(Collectors.groupingBy(
                        selection.equalsIgnoreCase("city") ? Contact::getCity : Contact::getState,
                        Collectors.counting()
                ));

        System.out.println("📊 Metric Totals Breakdown:");
        countMap.forEach((location, count) -> System.out.println(" - " + location + ": " + count));
    }
}
