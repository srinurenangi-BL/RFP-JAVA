package Day30;
import java.util.List;

public class InvoiceGenerator {
    private final RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    // Direct access to repository injects seamless architecture decoupling
    public void addRidesToRepository(String userId, List<Ride> rides) {
        rideRepository.addRides(userId, rides);
    }

    // UC 1: Calculate single ride fare
    public double calculateFare(double distance, int time) {
        return RideCategory.NORMAL.calculateFare(distance, time);
    }

    // UC 2 & UC 3: Aggregate Multiple Rides into Invoice Summary
    public InvoiceSummary calculateFare(List<Ride> rides) {
        double totalFare = rides.stream()
                .mapToDouble(Ride::getFare)
                .sum();
        return new InvoiceSummary(rides.size(), totalFare);
    }

    // UC 4: Invoice Service using UserId lookup
    public InvoiceSummary getInvoiceSummary(String userId) {
        List<Ride> rides = this.rideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
