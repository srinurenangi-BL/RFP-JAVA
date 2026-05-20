package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.AddressBookDataIO;
import com.opencsv.bean.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CsvDataEngine implements AddressBookDataIO {
    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String src) {
        return CompletableFuture.supplyAsync(() -> {
            try (Reader reader = new FileReader(src)) {
                CsvToBean<Contact> parser = new CsvToBeanBuilder<Contact>(reader)
                        .withType(Contact.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                return parser.parse();
            } catch (IOException e) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String src) {
        return CompletableFuture.supplyAsync(() -> {
            try (Writer writer = new FileWriter(src)) {
                StatefulBeanToCsv<Contact> exporter = new StatefulBeanToCsvBuilder<Contact>(writer).build();
                exporter.write(contacts);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}