package Day13;

public class MaximumInteger {
    public static Integer findMax(Integer x, Integer y, Integer z) {
        Integer max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        // TC 1.1: Max at 1st Position
        System.out.println("TC 1.1: " + findMax(100, 50, 20)); 
        
        // TC 1.2: Max at 2nd Position
        System.out.println("TC 1.2: " + findMax(20, 100, 50)); 
        
        // TC 1.3: Max at 3rd Position
        System.out.println("TC 1.3: " + findMax(20, 50, 100)); 
    }
}