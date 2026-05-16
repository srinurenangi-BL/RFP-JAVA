package Day21.Mood_Analyzer_Problem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoodAnalyserTest {

    // TC 1.1 Repeat: Given "I am in Sad Mood" message in Constructor -> Should Return SAD
    @Test
    public void givenMessage_WhenSad_ShouldReturnSad() throws MoodAnalysisException {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in Sad Mood");
        String mood = moodAnalyser.analyseMood();
        assertEquals("SAD", mood);
    }

    // TC 1.2 Repeat: Given "I am in Happy Mood" message in Constructor -> Should Return HAPPY
    @Test
    public void givenMessage_WhenHappy_ShouldReturnHappy() throws MoodAnalysisException {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in Happy Mood");
        String mood = moodAnalyser.analyseMood();
        assertEquals("HAPPY", mood);
    }

    // UC 2 / TC 2.1 Fallback Check: Basic handling visualization
    @Test
    public void givenMessage_WhenAnyOtherMood_ShouldDefaultToHappy() throws MoodAnalysisException {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in Any Mood");
        String mood = moodAnalyser.analyseMood();
        assertEquals("HAPPY", mood);
    }

    // TC 3.1: Given NULL Mood Should Throw MoodAnalysisException (ENTERED_NULL)
    @Test
    public void givenNullMood_ShouldThrowMoodAnalysisException_IndicatingNull() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        
        MoodAnalysisException exception = assertThrows(MoodAnalysisException.class, () -> {
            moodAnalyser.analyseMood();
        });
        
        assertEquals(MoodAnalysisException.ExceptionType.ENTERED_NULL, exception.getType());
        assertEquals("Mood message cannot be null", exception.getMessage());
    }

    // TC 3.2: Given Empty Mood Should Throw MoodAnalysisException (ENTERED_EMPTY)
    @Test
    public void givenEmptyMood_ShouldThrowMoodAnalysisException_IndicatingEmpty() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("");
        
        MoodAnalysisException exception = assertThrows(MoodAnalysisException.class, () -> {
            moodAnalyser.analyseMood();
        });
        
        assertEquals(MoodAnalysisException.ExceptionType.ENTERED_EMPTY, exception.getType());
        assertEquals("Mood message cannot be empty", exception.getMessage());
    }
}