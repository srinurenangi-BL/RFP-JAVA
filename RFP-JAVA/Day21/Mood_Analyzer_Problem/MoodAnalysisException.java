package Day21.Mood_Analyzer_Problem;

public class MoodAnalysisException extends Exception {

    // Enum to distinguish error categories
    public enum ExceptionType {
        ENTERED_NULL,
        ENTERED_EMPTY
    }

    private final ExceptionType type;

    public MoodAnalysisException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() {
        return this.type;
    }
}
