package Day28;
import java.io.*;
import java.util.List;

public class AddressBookFileIOService {
    private static final String FILE_NAME = "addressbook-data.txt";

    // UC 12: Write contacts to plain text file
    public void writeData(List<ContactPerson> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ContactPerson contact : contacts) {
                writer.write(contact.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
    }

    // UC 12: Read contacts from plain text file
    public void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }
    }
}
