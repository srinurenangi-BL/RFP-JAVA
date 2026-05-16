package Day20;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MoodAnalyserTest {

    @Test
    public void givenMessage_WhenSad_ShouldReturnSad() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in a Sad mood");
        String mood = moodAnalyser.analyseMood();
        assertEquals("SAD", mood);
    }

    @Test
    public void givenMessage_WhenAnyOtherMood_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in a Happy mood");
        String mood = moodAnalyser.analyseMood();
        assertEquals("HAPPY", mood);
    }

    @Test
    public void givenMessage_WhenNull_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        String mood = moodAnalyser.analyseMood();
        assertEquals("HAPPY", mood);
    }
}
