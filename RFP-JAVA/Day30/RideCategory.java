package Day30;
public enum RideCategory {
    NORMAL(10.0, 1.0, 5.0),
    PREMIUM(15.0, 2.0, 20.0);

    private final double costPerKm;
    private final double costPerMin;
    private final double minimumFare;

    RideCategory(double costPerKm, double costPerMin, double minimumFare) {
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
        this.minimumFare = minimumFare;
    }

    public double calculateFare(double distance, int time) {
        double totalFare = (distance * this.costPerKm) + (time * this.costPerMin);
        return Math.max(totalFare, this.minimumFare);
    }
}
