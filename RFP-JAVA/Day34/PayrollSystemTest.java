package Day34;
import Day34.PayrollSystemException;
import Day34.EmployeePayrollData;
import Day34.PayrollDBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PayrollSystemTest {

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabaseState() throws PayrollSystemException {
        // Arrange
        PayrollDBService dbService = PayrollDBService.getInstance();
        String employeeName = "Terissa";
        double revisedSalary = 3000000.00;

        // Act
        boolean isUpdated = dbService.updateEmployeeSalary(employeeName, revisedSalary);
        List<EmployeePayrollData> databaseRecords = dbService.getAllEmployees();
        EmployeePayrollData terissaRecord = databaseRecords.stream()
                .filter(emp -> emp.name.equals(employeeName))
                .findFirst()
                .orElse(null);

        // Assert
        Assertions.IsTrue(isUpdated, "The database update script failed to execute.");
        Assertions.IsNotNull(terissaRecord, "Target employee record was missing from recovery lists.");
        Assertions.ScaleEquals(revisedSalary, terissaRecord.basicPay, 0.001, "The basic pay field did not sync properly across layers.");
    }
}