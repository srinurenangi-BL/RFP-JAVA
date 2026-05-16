package Day26;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HotelReservationService {
    private final List<Hotel> hotels = new ArrayList<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMMyyyy");

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    // UC 10 & 11: Parses string elements and validates patterns using Regex
    public List<LocalDate> parseAndValidateDates(List<String> dateStrings) throws HotelReservationException {
        // Example structure rule: 11Sep2020
        String dateRegex = "^[0-9]{2}[A-Z][a-z]{2}[0-9]{4}$";
        List<LocalDate> parsedDates = new ArrayList<>();

        for (String dStr : dateStrings) {
            String cleanStr = dStr.trim().replaceAll("\\([a-zA-Z]+\\)", ""); // Strips raw day flags (e.g. '(mon)')
            if (!Pattern.matches(dateRegex, cleanStr)) {
                throw new HotelReservationException(HotelReservationException.ExceptionType.INVALID_DATE_FORMAT, "Date pattern must conform to 'ddMMMyyyy' layout.");
            }
            try {
                parsedDates.add(LocalDate.parse(cleanStr, DATE_FORMATTER));
            } catch (DateTimeParseException e) {
                throw new HotelReservationException(HotelReservationException.ExceptionType.INVALID_DATE_FORMAT, "Failed to parse structured date token.");
            }
        }
        return parsedDates;
    }

    // UC 11 & UC 12: Evaluates reservation constraints using Java Streams
    public String findCheapestBestRatedHotel(String customerType, List<String> rawDates) throws HotelReservationException {
        if (!customerType.equalsIgnoreCase("regular") && !customerType.equalsIgnoreCase("rewards")) {
            throw new HotelReservationException(HotelReservationException.ExceptionType.INVALID_CUSTOMER_TYPE, "Unrecognized tier: Must specify Regular or Rewards.");
        }

        List<LocalDate> dateRange = parseAndValidateDates(rawDates);
        if (dateRange.isEmpty()) {
            throw new HotelReservationException(HotelReservationException.ExceptionType.INVALID_DATE_RANGE, "Date matrix collection cannot evaluate empty inputs.");
        }

        // Internal tracking wrapper class for stream collection operations
        class HotelCostSummary {
            final Hotel hotel;
            final int totalCost;

            HotelCostSummary(Hotel hotel) {
                this.hotel = hotel;
                this.totalCost = hotel.calculateTotalCost(dateRange, customerType);
            }
            Hotel hotel() { return hotel; }
            int cost() { return totalCost; }
            int rating() { return hotel.getRating(); }
        }

        // Unified pipeline filtering parameters using Java Streams
        HotelCostSummary bestOption = hotels.stream()
                .map(HotelCostSummary::new)
                .min(Comparator.comparingInt(HotelCostSummary::cost)
                        .thenComparing(Comparator.comparingInt(HotelCostSummary::rating).reversed()))
                .orElseThrow(() -> new IllegalStateException("Zero hotels populated within framework."));

        return String.format("%s, Rating: %d and Total Rates: $%d", 
                bestOption.hotel().getName(), bestOption.rating(), bestOption.cost());
    }

    // UC 7: Independent pipeline targeting highest structural rating profile
    public String findBestRatedHotel(String customerType, List<String> rawDates) throws HotelReservationException {
        List<LocalDate> dateRange = parseAndValidateDates(rawDates);
        
        Hotel bestRated = hotels.stream()
                .max(Comparator.comparingInt(Hotel::getRating))
                .orElseThrow(() -> new IllegalStateException("Zero hotels populated within framework."));

        int totalCost = bestRated.calculateTotalCost(dateRange, customerType);
        return String.format("%s & Total Rates $%d", bestRated.getName(), totalCost);
    }
}
