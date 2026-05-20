using System;
using System.Collections.Generic;
using System.Data;
using Microsoft.Data.SqlClient;

public class AddressBookRepository
{
    private readonly string connectionString = "Data Source=(localdb)\\MSSQLLocalDB;Initial Catalog=address_book_service;Integrated Security=True;";

    // UC 6 & UC 13: Read contacts safely out of normalized architectures
    public List<ContactData> GetContactsByCity(string targetCity)
    {
        List<ContactData> listings = new List<ContactData>();
        string query = @"SELECT c.ContactID, c.FirstName, c.LastName, c.Address, c.City, c.State, c.Zip, c.PhoneNumber, c.Email, b.TypeName
                         FROM Contact c
                         LEFT JOIN Contact_Type_Mapping m ON c.ContactID = m.ContactID
                         LEFT JOIN Book_Type b ON m.TypeID = b.TypeID
                         WHERE c.City = @City;";

        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            SqlCommand cmd = new SqlCommand(query, conn);
            cmd.Parameters.Add("@City", SqlDbType.VarChar).Value = targetCity;
            
            conn.Open();
            using (SqlDataReader reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    int id = reader.GetInt32(0);
                    ContactData item = listings.Find(x => x.ContactID == id);

                    if (item == null)
                    {
                        item = new ContactData
                        {
                            ContactID = id,
                            FirstName = reader.GetString(1),
                            LastName = reader.GetString(2),
                            Address = reader.GetString(3),
                            City = reader.GetString(4),
                            State = reader.GetString(5),
                            Zip = reader.GetString(6),
                            PhoneNumber = reader.GetString(7),
                            Email = reader.GetString(8)
                        };
                        listings.Add(item);
                    }

                    if (!reader.IsDBNull(9))
                    {
                        item.ContactTypes.Add(reader.GetString(9));
                    }
                }
            }
        }
        return listings;
    }

    // UC 4: Update Records Safe from SQL Injections
    public bool UpdateContactAddress(string firstName, string lastName, string newAddress)
    {
        string query = @"UPDATE Contact 
                         SET Address = @Address 
                         WHERE FirstName = @FirstName AND LastName = @LastName;";

        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            SqlCommand cmd = new SqlCommand(query, conn);
            cmd.Parameters.Add("@Address", SqlDbType.VarChar).Value = newAddress;
            cmd.Parameters.Add("@FirstName", SqlDbType.VarChar).Value = firstName;
            cmd.Parameters.Add("@LastName", SqlDbType.VarChar).Value = lastName;

            conn.Open();
            cmd.Prepare(); // Pre-compiles execution path pattern inside engine context
            int executionImpact = cmd.ExecuteNonQuery();
            return executionImpact > 0;
        }
    }

    // UC 3 & UC 11: Atomic Database Transaction for Data Additions
    public bool AddNewContactWithTypes(ContactData contact)
    {
        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            conn.Open();
            SqlTransaction tx = conn.BeginTransaction();

            try
            {
                // Write into base profile collection
                string insertContactQuery = @"INSERT INTO Contact (FirstName, LastName, Address, City, State, Zip, PhoneNumber, Email)
                                              OUTPUT INSERTED.ContactID
                                              VALUES (@FirstName, @LastName, @Address, @City, @State, @Zip, @PhoneNumber, @Email);";

                SqlCommand contactCmd = new SqlCommand(insertContactQuery, conn, tx);
                contactCmd.Parameters.AddWithValue("@FirstName", contact.FirstName);
                contactCmd.Parameters.AddWithValue("@LastName", contact.LastName);
                contactCmd.Parameters.AddWithValue("@Address", contact.Address);
                contactCmd.Parameters.AddWithValue("@City", contact.City);
                contactCmd.Parameters.AddWithValue("@State", contact.State);
                contactCmd.Parameters.AddWithValue("@Zip", contact.Zip);
                contactCmd.Parameters.AddWithValue("@PhoneNumber", contact.PhoneNumber);
                contactCmd.Parameters.AddWithValue("@Email", contact.Email);

                int newlyGeneratedID = (int)contactCmd.ExecuteScalar();

                // Build structural classification ties safely
                foreach (string typeName in contact.ContactTypes)
                {
                    // Check if category definition type exists
                    string getTypeIDQuery = "SELECT TypeID FROM Book_Type WHERE TypeName = @TypeName;";
                    SqlCommand typeCmd = new SqlCommand(getTypeIDQuery, conn, tx);
                    typeCmd.Parameters.AddWithValue("@TypeName", typeName);
                    
                    object typeResult = typeCmd.ExecuteScalar();
                    int typeID;

                    if (typeResult == null)
                    {
                        // Dynamic fallback insertion if a classification option is missing
                        string createTypeQuery = "INSERT INTO Book_Type (BookName, TypeName) OUTPUT INSERTED.TypeID VALUES ('DynamicBook', @TypeName);";
                        SqlCommand dynamicTypeCmd = new SqlCommand(createTypeQuery, conn, tx);
                        dynamicTypeCmd.Parameters.AddWithValue("@TypeName", typeName);
                        typeID = (int)dynamicTypeCmd.ExecuteScalar();
                    }
                    else
                    {
                        typeID = (int)typeResult;
                    }

                    // Map junction table relationships
                    string mapQuery = "INSERT INTO Contact_Type_Mapping (ContactID, TypeID) VALUES (@ContactID, @TypeID);";
                    SqlCommand mapCmd = new SqlCommand(mapQuery, conn, tx);
                    mapCmd.Parameters.AddWithValue("@ContactID", newlyGeneratedID);
                    mapCmd.Parameters.AddWithValue("@TypeID", typeID);
                    mapCmd.ExecuteNonQuery();
                }

                tx.Commit();
                return true;
            }
            catch (Exception)
            {
                tx.Rollback();
                throw;
            }
        }
    }
}