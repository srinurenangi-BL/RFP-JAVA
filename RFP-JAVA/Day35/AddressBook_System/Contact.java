package Day35.AddressBook_System;

import com.opencsv.bean.CsvBindByName;
import java.time.LocalDate;
import java.util.Objects;

public class Contact {
    private int contactID;
    
    @CsvBindByName(column = "firstName")
    private String firstName;
    
    @CsvBindByName(column = "lastName")
    private String lastName;
    
    @CsvBindByName(column = "address")
    private String address;
    
    @CsvBindByName(column = "city")
    private String city;
    
    @CsvBindByName(column = "state")
    private String state;
    
    @CsvBindByName(column = "zip")
    private String zip;
    
    @CsvBindByName(column = "phoneNumber")
    private String phoneNumber;
    
    @CsvBindByName(column = "email")
    private String email;
    
    private LocalDate dateAdded = LocalDate.now();

    // Getters and Setters
    public int getContactID() { return contactID; }
    public void setContactID(int contactID) { this.contactID = contactID; }
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
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }

    // UC 7: Duplicate prevention logic matching criteria specs
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
        return String.format("Name: %s %s | Addr: %s, %s, %s %s | Phone: %s | Email: %s",
                firstName, lastName, address, city, state, zip, phoneNumber, email);
    }
}