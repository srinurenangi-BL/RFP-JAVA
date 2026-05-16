package Day26;


public class HotelReservationException extends Exception {
    public enum ExceptionType {
        INVALID_CUSTOMER_TYPE,
        INVALID_DATE_FORMAT,
        INVALID_DATE_RANGE
    }

    private final ExceptionType type;

    public HotelReservationException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() { return type; }
}
