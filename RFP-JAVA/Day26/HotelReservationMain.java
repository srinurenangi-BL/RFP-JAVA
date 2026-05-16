package Day26;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HotelReservationMain {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("Welcome to Hotel Reservation Program");
        System.out.println("=========================================");

        HotelReservationService service = new HotelReservationService();

        // Seed default hotel data patterns (UC 1, UC 3, UC 5, UC 9 mapping)
        service.addHotel(new Hotel("Lakewood", 3, 110, 90, 80, 80));
        service.addHotel(new Hotel("Bridgewood", 4, 160, 60, 110, 50));
        service.addHotel(new Hotel("Ridgewood", 5, 220, 150, 100, 40));

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter reservation request string (e.g., 'Regular: 16Mar2020(mon), 17Mar2020(tues)'):");
        String inputLine = scanner.nextLine();

        try {
            // Split input into customer type and date fields
            String[] inputParts = inputLine.split(":");
            if (inputParts.length != 2) {
                System.out.println("❌ Invalid raw execution block. Follow format: <customer_type>: <date1>, <date2>");
                return;
            }

            String customerType = inputParts[0].trim();
            List<String> dateStrings = Arrays.stream(inputParts[1].split(","))
                                             .map(String::trim)
                                             .collect(Collectors.toList());

            String outcomeResult = service.findCheapestBestRatedHotel(customerType, dateStrings);
            System.out.println("\n🎯 Result: " + outcomeResult);

        } catch (HotelReservationException e) {
            System.out.println("⚠️ Validation Error Triggered: [" + e.getType() + "] -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Critical runtime engine execution interruption: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
