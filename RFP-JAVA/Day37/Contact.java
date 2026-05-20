package Day37;

import com.opencsv.bean.CsvBindByName;
import java.util.Objects;

public class Contact {
    @CsvBindByName(column = "firstName") private String firstName;
    @CsvBindByName(column = "lastName") private String lastName;
    @CsvBindByName(column = "address") private String address;
    @CsvBindByName(column = "city") private String city;
    @CsvBindByName(column = "state") private String state;
    @CsvBindByName(column = "zip") private String zip;
    @CsvBindByName(column = "phoneNumber") private String phoneNumber;

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // UC 6: Check for duplicates by overriding equals() for Stream parsing matching
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return firstName.equalsIgnoreCase(contact.firstName) && 
               lastName.equalsIgnoreCase(contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s %s | %s, %s, %s | Ph: %s", firstName, lastName, address, city, state, phoneNumber);
    }
}