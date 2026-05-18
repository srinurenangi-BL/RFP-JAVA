package Day30;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    private final Map<String, List<Ride>> userRidesMap;

    public RideRepository() {
        this.userRidesMap = new HashMap<>();
    }

    public void addRides(String userId, List<Ride> rides) {
        this.userRidesMap.computeIfAbsent(userId, k -> new ArrayList<>()).addAll(rides);
    }

    public List<Ride> getRides(String userId) {
        return this.userRidesMap.getOrDefault(userId, new ArrayList<>());
    }
}
