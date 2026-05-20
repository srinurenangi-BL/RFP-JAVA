package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.AddressBookDataIO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JsonDataEngine implements AddressBookDataIO {
    private final Gson gson = new Gson();

    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String src) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (!Files.exists(Paths.get(src))) return new ArrayList<>();
                Reader reader = Files.newBufferedReader(Paths.get(src));
                List<Contact> list = gson.fromJson(reader, new TypeToken<List<Contact>>(){}.getType());
                reader.close();
                return list != null ? list : new ArrayList<>();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String src) {
        return CompletableFuture.supplyAsync(() -> {
            try (Writer writer = new FileWriter(src)) {
                gson.toJson(contacts, writer);
                return true;
            } catch (IOException e) {
                return false;
            }
        });
    }
}