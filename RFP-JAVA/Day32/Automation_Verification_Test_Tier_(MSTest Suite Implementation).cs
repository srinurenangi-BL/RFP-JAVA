using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

[TestClass]
public class PayrollSystemTestingSuite
{
    [TestMethod]
    public void GivenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase()
    {
        // Arrange
        PayrollDBService service = PayrollDBService.GetInstance();
        string employeeName = "Terissa";
        decimal updatedSalary = 3000000.00m;

        // Act
        bool isUpdated = service.UpdateEmployeeSalary(employeeName, updatedSalary);
        List<EmployeePayrollData> empiricalList = service.GetAllEmployees();
        EmployeePayrollData targetedRecord = empiricalList.Find(e => e.EmployeeName == employeeName);

        // Assert
        Assert.IsTrue(isUpdated);
        Assert.IsNotNull(targetedRecord);
        Assert.AreEqual(updatedSalary, targetedRecord.BasicPay);
    }
}