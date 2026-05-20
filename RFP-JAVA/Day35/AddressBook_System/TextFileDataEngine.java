package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.AddressBookDataIO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TextFileDataEngine implements AddressBookDataIO {
    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String src) {
        return CompletableFuture.supplyAsync(() -> {
            List<Contact> contacts = new ArrayList<>();
            try {
                if (!Files.exists(Paths.get(src))) return contacts;
                List<String> lines = Files.readAllLines(Paths.get(src));
                for (String line : lines) {
                    String[] data = line.split("\\|");
                    if (data.length >= 8) {
                        Contact c = new Contact();
                        c.setFirstName(data[0]); c.setLastName(data[1]); c.setAddress(data[2]);
                        c.setCity(data[3]); c.setState(data[4]); c.setZip(data[5]);
                        c.setPhoneNumber(data[6]); c.setEmail(data[7]);
                        contacts.add(c);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return contacts;
        });
    }

    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String src) {
        return CompletableFuture.supplyAsync(() -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter(src))) {
                for (Contact c : contacts) {
                    writer.println(String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                            c.getFirstName(), c.getLastName(), c.getAddress(), c.getCity(),
                            c.getState(), c.getZip(), c.getPhoneNumber(), c.getEmail()));
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        });
    }
}