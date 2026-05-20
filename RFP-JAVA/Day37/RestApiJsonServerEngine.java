package Day37;

import Day37.Contact;
import Day37.AddressBookDataIO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RestApiJsonServerEngine implements AddressBookDataIO {
    private final String baseEndpoint = "http://localhost:3000/contacts";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String ignored) {
        return CompletableFuture.supplyAsync(() -> {
            Request request = new Request.Builder().url(baseEndpoint).get().build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) return new ArrayList<>();
                return gson.fromJson(response.body().string(), new TypeToken<List<Contact>>(){}.getType());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String ignored) {
        return CompletableFuture.supplyAsync(() -> {
            for (Contact c : contacts) {
                RequestBody body = RequestBody.create(
                        gson.toJson(c), MediaType.get("application/json; charset=utf-8"));
                Request request = new Request.Builder().url(baseEndpoint).post(body).build();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) return false;
                } catch (IOException e) {
                    return false;
                }
            }
            return true;
        });
    }
}