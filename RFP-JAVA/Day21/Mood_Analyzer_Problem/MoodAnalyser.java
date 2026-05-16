package Day21.Mood_Analyzer_Problem;

public class MoodAnalyser {
    private String message;

    // Default Constructor
    public MoodAnalyser() {
        this.message = "Default";
    }

    // Parameterized Constructor (Refactor Step)
    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood() throws MoodAnalysisException {
        // UC 3: Handle NULL mood scenario explicitly
        if (message == null) {
            throw new MoodAnalysisException(
                MoodAnalysisException.ExceptionType.ENTERED_NULL, 
                "Mood message cannot be null"
            );
        }
        
        // UC 3: Handle EMPTY mood scenario explicitly
        if (message.trim().isEmpty()) {
            throw new MoodAnalysisException(
                MoodAnalysisException.ExceptionType.ENTERED_EMPTY, 
                "Mood message cannot be empty"
            );
        }

        // UC 1: Standard logic check
        if (message.toLowerCase().contains("sad")) {
            return "SAD";
        } else {
            return "HAPPY";
        }
    }
}
