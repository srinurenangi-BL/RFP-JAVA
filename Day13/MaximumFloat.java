package Day13;

public class MaximumFloat {
    public static Float findMax(Float x, Float y, Float z) {
        Float max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        // TC 2.1: Max at 1st Position
        System.out.println("TC 2.1: " + findMax(15.5f, 10.2f, 5.5f));
        
        // TC 2.2: Max at 2nd Position
        System.out.println("TC 2.2: " + findMax(5.5f, 15.5f, 10.2f));
        
        // TC 2.3: Max at 3rd Position
        System.out.println("TC 2.3: " + findMax(5.5f, 10.2f, 15.5f));
    }
}