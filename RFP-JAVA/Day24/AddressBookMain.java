package Day24;

import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        // START Master Branch welcome banner
        System.out.println("=========================================");
        System.out.println("Welcome to Address Book Program");
        System.out.println("=========================================");

        AddressBookSystem systemManager = new AddressBookSystem();
        Scanner sc = new Scanner(System.in);

        // Seed an initial runtime container environment
        systemManager.createNewAddressBook("DefaultBook");
        String activeBookName = "DefaultBook";

        while (true) {
            System.out.println("\n--- Current Active Book: [" + activeBookName + "] ---");
            System.out.println("1. Add Contact | 2. Edit Contact | 3. Delete Contact");
            System.out.println("4. Switch / Add Address Book     | 5. Global Search (City/State)");
            System.out.println("6. Dictionary Views              | 7. Distribution Metrics Counter");
            System.out.println("8. Print Sorted Contacts         | 9. Terminate Application");
            System.out.print("Select operational route: ");
            
            int select = Integer.parseInt(sc.nextLine());

            switch (select) {
                case 1: // Add Contact (UC 1, UC 4, UC 7)
                    System.out.print("Enter First Name: "); String f = sc.nextLine();
                    System.out.print("Enter Last Name: ");  String l = sc.nextLine();
                    System.out.print("Enter Address: ");    String ad = sc.nextLine();
                    System.out.print("Enter City: ");       String ci = sc.nextLine();
                    System.out.print("Enter State: ");      String st = sc.nextLine();
                    System.out.print("Enter Zip: ");        String z = sc.nextLine();
                    System.out.print("Enter Phone: ");      String ph = sc.nextLine();
                    System.out.print("Enter Email: ");      String em = sc.nextLine();

                    Contact contact = new Contact(f, l, ad, ci, st, z, ph, em);
                    boolean added = systemManager.getAddressBook(activeBookName).addContact(contact);
                    if (added) System.out.println("✅ Contact successfully logged into system.");
                    break;

                case 2: // Edit Contact (UC 3)
                    System.out.print("Target First Name: "); String ef = sc.nextLine();
                    System.out.print("Target Last Name: ");  String el = sc.nextLine();
                    System.out.print("New Address: ");       String nad = sc.nextLine();
                    System.out.print("New City: ");          String nci = sc.nextLine();
                    System.out.print("New State: ");         String nst = sc.nextLine();
                    System.out.print("New Zip: ");           String nz = sc.nextLine();
                    System.out.print("New Phone: ");         String nph = sc.nextLine();
                    System.out.print("New Email: ");         String nem = sc.nextLine();

                    boolean updated = systemManager.getAddressBook(activeBookName).editContact(ef, el, nad, nci, nst, nz, nph, nem);
                    System.out.println(updated ? "✅ Record updated successfully." : "❌ Targeted record not located.");
                    break;

                case 3: // Delete Contact (UC 4)
                    System.out.print("Target First Name: "); String df = sc.nextLine();
                    System.out.print("Target Last Name: ");  String dl = sc.nextLine();
                    boolean removed = systemManager.getAddressBook(activeBookName).deleteContact(df, dl);
                    System.out.println(removed ? "✅ Record removed." : "❌ Record not found.");
                    break;

                case 4: // Switch or Add Book (UC 6)
                    System.out.println("Existing Address Books: " + systemManager.getBookNames());
                    System.out.print("Target Book Name to select or generate: ");
                    String selectedBook = sc.nextLine();
                    systemManager.createNewAddressBook(selectedBook);
                    activeBookName = selectedBook;
                    break;

                case 5: // Search Across Systems (UC 8)
                    System.out.print("Enter City or State search string value: ");
                    List<Contact> searchResult = systemManager.searchByCityOrState(sc.nextLine());
                    if(searchResult.isEmpty()) System.out.println("No matching entries found.");
                    else searchResult.forEach(c -> System.out.println(c));
                    break;

                case 6: // Dictionary Views (UC 9)
                    System.out.println("\n--- View by Cities ---");
                    systemManager.getGroupedByCity().forEach((k, v) -> System.out.println(k + " -> " + v));
                    System.out.println("\n--- View by States ---");
                    systemManager.getGroupedByState().forEach((k, v) -> System.out.println(k + " -> " + v));
                    break;

                case 7: // Distribution Metric Aggregations (UC 10)
                    System.out.print("Enter targeted location name query: ");
                    String lookupLoc = sc.nextLine();
                    System.out.println("City matching instances: " + systemManager.countByCity(lookupLoc));
                    System.out.println("State matching instances: " + systemManager.countByState(lookupLoc));
                    break;

                case 8: // Sorting Views (UC 11, UC 12)
                    System.out.print("Enter sorting criteria (name / city / state / zip): ");
                    List<Contact> sorted = systemManager.getAddressBook(activeBookName).getSortedEntries(sc.nextLine());
                    sorted.forEach(System.out::println);
                    break;

                case 9:
                    System.out.println("Exiting System. Goodbye!");
                    sc.close();
                    return;
            }
        }
    }
}
