package Day21.Mood_Analyzer_Problem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MoodAnalyserReflectionDemo {
    public static void main(String[] args) {
        try {
            // 1. Get the class blueprint definition matching our namespace
            Class<?> moodAnalyserClass = Class.forName("com.moodanalyzer.MoodAnalyser");

            // 2. Fetch the constructor signature accepting a single String argument
            Constructor<?> stringConstructor = moodAnalyserClass.getConstructor(String.class);

            // 3. Dynamically instantiate the object instance using our signature
            Object moodAnalyserInstance = stringConstructor.newInstance("I am in a Sad Mood");

            // 4. Extract the method blueprint for "analyseMood"
            Method analyseMoodMethod = moodAnalyserClass.getDeclaredMethod("analyseMood");

            // 5. Invoke the method on our dynamic object instance
            Object resultingMood = analyseMoodMethod.invoke(moodAnalyserInstance);

            // Output the runtime returned results
            System.out.println("Reflection Invocation Result: " + resultingMood); // Prints: SAD

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}