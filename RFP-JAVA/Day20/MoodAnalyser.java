package Day20;

public class MoodAnalyser {
    private String message;

    public MoodAnalyser() {}

    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood() {
        try {
            if (message.contains("Sad")) {
                return "SAD";
            }
            return "HAPPY";
        } catch (NullPointerException e) {
            return "HAPPY"; // Default fallback behavior
        }
    }
}
