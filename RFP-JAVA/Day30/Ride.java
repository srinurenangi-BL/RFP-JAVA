package Day30;
public class Ride {
    private final double distance;
    private final int time;
    private final RideCategory category;

    // Default constructor defaults to NORMAL ride for backward compatibility (UC 1 - UC 4)
    public Ride(double distance, int time) {
        this(distance, time, RideCategory.NORMAL);
    }

    // UC 5: Premium Rides constructor
    public Ride(double distance, int time, RideCategory category) {
        this.distance = distance;
        this.time = time;
        this.category = category;
    }

    public double getFare() {
        return this.category.calculateFare(this.distance, this.time);
    }
}