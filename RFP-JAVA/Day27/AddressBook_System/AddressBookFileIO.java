package Day27.AddressBook_System;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AddressBookFileIO {
    private static final String FILE_NAME = "AddressBookData.txt";

    // UC 13: Write entire Address Book dictionary mapping structure to File
    public static void writeData(Map<String, AddressBook> addressBookMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
                writer.write("---BOOK:" + entry.getKey() + "---\n");
                for (Contact c : entry.getValue().getContactList()) {
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
                            c.getFirstName(), c.getLastName(), c.getAddress(),
                            c.getCity(), c.getState(), c.getZip(), c.getPhoneNumber(), c.getEmail()));
                }
            }
            System.out.println("💾 System State safely saved to local disk storage layer.");
        } catch (IOException e) {
            System.out.println("❌ Error writing storage checkpoint: " + e.getMessage());
        }
    }

    // UC 13: Read existing dynamic datasets during runtime environment bootstrapping
    public static Map<String, AddressBook> readData() {
        Map<String, AddressBook> map = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return map;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            AddressBook currentBook = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("---BOOK:")) {
                    String bookName = line.replace("---BOOK:", "").replace("---", "");
                    currentBook = new AddressBook(bookName);
                    map.put(bookName, currentBook);
                } else if (currentBook != null && !line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    if (data.length == 8) {
                        Contact c = new Contact(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
                        currentBook.getContactList().add(c);
                    }
                }
            }
            System.out.println("📂 Initializing dataset structures from local file footprint...");
        } catch (IOException e) {
            System.out.println("⚠️ Warning reading storage state: " + e.getMessage());
        }
        return map;
    }
}
