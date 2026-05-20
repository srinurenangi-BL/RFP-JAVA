package Day35.AddressBook_System;

import Day35.AddressBook_System.Contact;
import Day35.AddressBook_System.AddressBookDataIO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JdbcDatabaseEngine implements AddressBookDataIO {
    private final String url = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
    private final String user = "root";
    private final String password = "password"; // Set to your target local profile instance context parameters

    @Override
    public CompletableFuture<List<Contact>> readDataAsync(String ignored) {
        return CompletableFuture.supplyAsync(() -> {
            List<Contact> list = new ArrayList<>();
            String sql = "SELECT contact_id, first_name, last_name, address, city, state, zip, phone_number, email, date_added FROM NormalizedContact";
            
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Contact c = new Contact();
                    c.setContactID(rs.getInt(1));
                    c.setFirstName(rs.getString(2));
                    c.setLastName(rs.getString(3));
                    c.setAddress(rs.getString(4));
                    c.setCity(rs.getString(5));
                    c.setState(rs.getString(6));
                    c.setZip(rs.getString(7));
                    c.setPhoneNumber(rs.getString(8));
                    c.setEmail(rs.getString(9));
                    c.setDateAdded(rs.getDate(10).toLocalDate());
                    list.add(c);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        });
    }

    // UC 20: Add new records inside an ACID-compliant transaction block
    @Override
    public CompletableFuture<Boolean> writeDataAsync(List<Contact> contacts, String bookClassificationName) {
        return CompletableFuture.supplyAsync(() -> {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false); // Begin ACID-compliant transaction boundaries

                String insertContactSql = "INSERT INTO NormalizedContact (first_name, last_name, address, city, state, zip, phone_number, email, date_added) VALUES (?,?,?,?,?,?,?,?,?)";
                
                for (Contact c : contacts) {
                    int keyGeneratedId = 0;
                    try (PreparedStatement stmt = conn.prepareStatement(insertContactSql, Statement.RETURN_GENERATED_KEYS)) {
                        stmt.setString(1, c.getFirstName());
                        stmt.setString(2, c.getLastName());
                        stmt.setString(3, c.getAddress());
                        stmt.setString(4, c.getCity());
                        stmt.setString(5, c.getState());
                        stmt.setString(6, c.getZip());
                        stmt.setString(7, c.getPhoneNumber());
                        stmt.setString(8, c.getEmail());
                        stmt.setDate(9, Date.valueOf(c.getDateAdded()));
                        stmt.executeUpdate();

                        try (ResultSet rs = stmt.getGeneratedKeys()) {
                            if (rs.next()) keyGeneratedId = rs.getInt(1);
                        }
                    }

                    String mapSql = "INSERT INTO ContactMappingJunction (contact_id, classification_id) " +
                                    "VALUES (?, (SELECT id FROM BookClassification WHERE book_name = ? LIMIT 1))";
                    try (PreparedStatement mapStmt = conn.prepareStatement(mapSql)) {
                        mapStmt.setInt(1, keyGeneratedId);
                        mapStmt.setString(2, bookClassificationName);
                        mapStmt.executeUpdate();
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                if (conn != null) {
                    try { conn.rollback(); } catch (SQLException ex) { System.err.println("Rollback failed"); }
                }
                return false;
            } finally {
                if (conn != null) { try { conn.close(); } catch (SQLException e) {}}
            }
        });
    }

    // UC 17: Update addresses safely using parameterized queries
    public boolean updateContactAddress(String first, String last, String nextAddr) {
        String sql = "UPDATE NormalizedContact SET address = ? WHERE first_name = ? AND last_name = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nextAddr);
            stmt.setString(2, first);
            stmt.setString(3, last);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}