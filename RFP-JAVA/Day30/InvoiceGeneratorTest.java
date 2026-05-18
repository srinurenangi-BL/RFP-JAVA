package Day30;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class InvoiceGeneratorTest {
    private InvoiceGenerator invoiceGenerator;

    @BeforeEach
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test // UC 1: Calculate Fare (Happy Path)
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25.0, fare, 0.0);
    }

    @Test // UC 1: Check Minimum Fare constraint
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(5.0, fare, 0.0);
    }

    @Test // UC 2 & UC 3: Enhanced Multiple Rides Invoice Summary
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        List<Ride> rides = Arrays.asList(
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        );
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedSummary, summary);
    }

    @Test // UC 4: Invoice Service getting User-specific List from Repository
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "user101";
        List<Ride> rides = Arrays.asList(
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(10.0, 10)
        );
        invoiceGenerator.addRidesToRepository(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 140.0);
        Assertions.assertEquals(expectedSummary, summary);
    }

    @Test // UC 5: Premium Rides Calculation Verification
    public void givenNormalAndPremiumRides_ShouldReturnCorrectSummary() {
        String userId = "premium_user";
        List<Ride> rides = Arrays.asList(
                new Ride(2.0, 5, RideCategory.NORMAL),   // 25.0
                new Ride(5.0, 10, RideCategory.PREMIUM)  // (5*15) + (10*2) = 75 + 20 = 95.0
        );
        invoiceGenerator.addRidesToRepository(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 120.0);
        Assertions.assertEquals(expectedSummary, summary);
    }
}