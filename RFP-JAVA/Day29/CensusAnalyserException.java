package Day29;
public class CensusAnalyserException extends Exception {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, INCORRECT_TYPE, DELIMITER_OR_HEADER_PROBLEM
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}