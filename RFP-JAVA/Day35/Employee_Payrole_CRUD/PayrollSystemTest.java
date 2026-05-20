package Day35.Employee_Payrole_CRUD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Day35.Employee_Payrole_CRUD.EmployeePayrollData;
import Day35.Employee_Payrole_CRUD.PayrollDBService;
import Day35.Employee_Payrole_CRUD.PayrollSystemException;

import java.util.List;

public class PayrollSystemTest {

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabaseState() throws PayrollSystemException {
        // Arrange
        PayrollDBService dbService = PayrollDBService.getInstance();
        String targetEmployee = "Terissa";
        double expectedBasicPay = 3000000.00;

        // Act
        boolean processingStatus = dbService.updateEmployeeSalary(targetEmployee, expectedBasicPay);
        List<EmployeePayrollData> accurateListings = dbService.getAllActiveEmployees();
        
        EmployeePayrollData terissaRecord = accurateListings.stream()
                .filter(emp -> emp.name.equals(targetEmployee))
                .findFirst()
                .orElse(null);

        // Assert
        Assertions.assertTrue(processingStatus, "The application failed to execute the update statement.");
        Assertions.assertNotNull(terissaRecord, "The updated employee record was not found in active listings.");
        Assertions.assertEquals(expectedBasicPay, terissaRecord.basicPay, 0.001, "The salary value did not match between memory and storage layers.");
    }
}
