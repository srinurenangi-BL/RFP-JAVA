using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

[TestClass]
public class AddressBookSystemTests
{
    [TestMethod]
    public void GivenNewAddress_WhenUpdated_ShouldSyncWithNormalizedDatabase()
    {
        // Arrange
        AddressBookRepository repo = new AddressBookRepository();
        string targetFirstName = "John";
        string targetLastName = "Doe";
        string revisedAddress = "999 Infinite Loop";
        string targetCity = "Miami";

        // Act
        bool actionResult = repo.UpdateContactAddress(targetFirstName, targetLastName, revisedAddress);
        List<ContactData> verifiedMatches = repo.GetContactsByCity(targetCity);
        ContactData selectedContact = verifiedMatches.Find(c => c.FirstName == targetFirstName && c.LastName == targetLastName);

        // Assert
        Assert.IsTrue(actionResult, "Database was not updated successfully.");
        Assert.IsNotNull(selectedContact, "Contact profile was not found.");
        Assert.AreEqual(revisedAddress, selectedContact.Address, "The address column failed to sync with the database.");
    }
}