package Day13;

public class MaximumString {
    public static String findMax(String x, String y, String z) {
        String max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        // TC 3.1: Max at 1st Position (Peach is alphabetically greater than Banana/Apple)
        System.out.println("TC 3.1: " + findMax("Peach", "Banana", "Apple"));
        
        // TC 3.2: Max at 2nd Position
        System.out.println("TC 3.2: " + findMax("Apple", "Peach", "Banana"));
        
        // TC 3.3: Max at 3rd Position
        System.out.println("TC 3.3: " + findMax("Apple", "Banana", "Peach"));
    }
}
