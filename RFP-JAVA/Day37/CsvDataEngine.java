package Day37;

import Day37.Contact;
import Day37.AddressBookDataIO;
import com.opencsv.bean.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CsvDataEngine implements AddressBookDataIO {
    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String path) {
        return CompletableFuture.supplyAsync(() -> {
            try (Reader reader = new FileReader(path)) {
                CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(reader)
                        .withType(Contact.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                return csvToBean.parse();
            } catch (IOException e) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String path) {
        return CompletableFuture.supplyAsync(() -> {
            try (Writer writer = new FileWriter(path)) {
                StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer).build();
                beanToCsv.write(contacts);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}