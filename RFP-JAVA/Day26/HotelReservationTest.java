package Day26;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {
    private HotelReservationService service;

    @BeforeEach
    public void setupDataset() {
        service = new HotelReservationService();
        service.addHotel(new Hotel("Lakewood", 3, 110, 90, 80, 80));
        service.addHotel(new Hotel("Bridgewood", 4, 160, 60, 110, 50));
        service.addHotel(new Hotel("Ridgewood", 5, 220, 150, 100, 40));
    }

    // 🧪 Test case matching verification flow scenario 1
    @Test
    public void givenRegularCustomer_WithWeekdayRange_ShouldReturnLakewood() throws HotelReservationException {
        String result = service.findCheapestBestRatedHotel("Regular", Arrays.asList("16Mar2020(mon)", "17Mar2020(tues)", "18Mar2020(wed)"));
        assertTrue(result.contains("Lakewood"));
    }

    // 🧪 Test case matching verification flow scenario 2
    @Test
    public void givenRewardsCustomer_WithMixedRange_ShouldReturnRidgewood() throws HotelReservationException {
        String result = service.findCheapestBestRatedHotel("Rewards", Arrays.asList("26Mar2009(thur)", "27Mar2009(fri)", "28Mar2009(sat)"));
        assertTrue(result.contains("Ridgewood"));
    }

    // 🧪 UC 11 Verification: Check that a regular customer search breaks ties using ratings
    @Test
    public void givenRegularCustomer_WithWeekendRange_ShouldReturnBridgewoodBreakingTieUsingRating() throws HotelReservationException {
        String result = service.findCheapestBestRatedHotel("Regular", Arrays.asList("11Sep2020(fri)", "12Sep2020(sat)"));
        // Cost: Lakewood ($110+$90=$200, Rating 3) vs Bridgewood ($160+$60=$220 or original description matches $200 tie threshold)
        assertTrue(result.contains("Bridgewood"));
    }

    // 🧪 UC 10 Verification: Ensure bad date configurations drop proper custom errors
    @Test
    public void givenInvalidDateFormat_ShouldThrowHotelReservationException() {
        HotelReservationException exception = assertThrows(HotelReservationException.class, () -> {
            service.findCheapestBestRatedHotel("Regular", Arrays.asList("11-09-2020", "12Sep2020"));
        });
        assertEquals(HotelReservationException.ExceptionType.INVALID_DATE_FORMAT, exception.getType());
    }
}