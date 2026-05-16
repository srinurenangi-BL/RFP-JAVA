package Day26;

public class Hotel {
    private final String name;
    private final int rating;
    private final int regularWeekdayRate;
    private final int regularWeekendRate;
    private final int rewardsWeekdayRate;
    private final int rewardsWeekendRate;

    public Hotel(String name, int rating, int regularWeekdayRate, int regularWeekendRate, int rewardsWeekdayRate, int rewardsWeekendRate) {
        this.name = name;
        this.rating = rating;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
        this.rewardsWeekdayRate = rewardsWeekdayRate;
        this.rewardsWeekendRate = rewardsWeekendRate;
    }

    public String getName() { return name; }
    public int getRating() { return rating; }

    // Computes dynamic stay cost using stay dates and customer membership profiles
    public int calculateTotalCost(java.util.List<java.time.LocalDate> dates, String customerType) {
        boolean isRewards = customerType.equalsIgnoreCase("rewards");
        int total = 0;
        for (java.time.LocalDate date : dates) {
            boolean isWeekend = (date.getDayOfWeek() == java.time.DayOfWeek.SATURDAY || 
                                 date.getDayOfWeek() == java.time.DayOfWeek.SUNDAY);
            if (isWeekend) {
                total += isRewards ? rewardsWeekendRate : regularWeekendRate;
            } else {
                total += isRewards ? rewardsWeekdayRate : regularWeekdayRate;
            }
        }
        return total;
    }
}
