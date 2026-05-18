package Day30;
import java.util.Objects;

public class InvoiceSummary {
    private final int totalNumberOfRides;
    private final double totalFare;
    private final double averageFare;

    public InvoiceSummary(int totalNumberOfRides, double totalFare) {
        this.totalNumberOfRides = totalNumberOfRides;
        this.totalFare = totalFare;
        this.averageFare = this.totalNumberOfRides == 0 ? 0 : this.totalFare / this.totalNumberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return totalNumberOfRides == that.totalNumberOfRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNumberOfRides, totalFare, averageFare);
    }

    @Override
    public String toString() {
        return String.format("Rides: %d | Total Fare: %.2f | Avg Fare: %.2f", 
                totalNumberOfRides, totalFare, averageFare);
    }
}