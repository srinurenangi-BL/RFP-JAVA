package Day2;

public class Operators {

    public static void main(String[] args) {

        // ==============================
        // 1. Arithmetic Operators
        // ==============================
        int a = 10, b = 3;

        System.out.println("Arithmetic Operators:");
        System.out.println("a + b = " + (a + b)); // Addition
        System.out.println("a - b = " + (a - b)); // Subtraction
        System.out.println("a * b = " + (a * b)); // Multiplication
        System.out.println("a / b = " + (a / b)); // Division
        System.out.println("a % b = " + (a % b)); // Modulus

        // ==============================
        // 2. Unary Operators
        // ==============================
        int x = 5;

        System.out.println("\nUnary Operators:");
        System.out.println("+x = " + (+x));       // Unary plus
        System.out.println("-x = " + (-x));       // Unary minus
        System.out.println("++x = " + (++x));     // Pre-increment
        System.out.println("--x = " + (--x));     // Pre-decrement

        // ==============================
        // 3. Relational (Comparison) Operators
        // ==============================
        System.out.println("\nRelational Operators:");
        System.out.println("a == b : " + (a == b));
        System.out.println("a != b : " + (a != b));
        System.out.println("a > b  : " + (a > b));
        System.out.println("a < b  : " + (a < b));
        System.out.println("a >= b : " + (a >= b));
        System.out.println("a <= b : " + (a <= b));

        // ==============================
        // 4. Logical Operators
        // ==============================
        boolean p = true, q = false;

        System.out.println("\nLogical Operators:");
        System.out.println("p && q : " + (p && q)); // AND
        System.out.println("p || q : " + (p || q)); // OR
        System.out.println("!p     : " + (!p));     // NOT

        // ==============================
        // 5. Bitwise Operators
        // ==============================
        int m = 5, n = 3;

        System.out.println("\nBitwise Operators:");
        System.out.println("m & n  : " + (m & n));  // AND
        System.out.println("m | n  : " + (m | n));  // OR
        System.out.println("m ^ n  : " + (m ^ n));  // XOR
        System.out.println("~m     : " + (~m));     // Complement

        // ==============================
        // 6. Shift Operators
        // ==============================
        System.out.println("\nShift Operators:");
        System.out.println("m << 1 : " + (m << 1)); // Left shift
        System.out.println("m >> 1 : " + (m >> 1)); // Right shift
        System.out.println("m >>> 1: " + (m >>> 1)); // Unsigned right shift

        // ==============================
        // 7. Assignment Operators
        // ==============================
        int y = 10;

        System.out.println("\nAssignment Operators:");
        y += 5;
        System.out.println("y += 5  -> " + y);
        y -= 3;
        System.out.println("y -= 3  -> " + y);
        y *= 2;
        System.out.println("y *= 2  -> " + y);
        y /= 4;
        System.out.println("y /= 4  -> " + y);
        y %= 3;
        System.out.println("y %= 3  -> " + y);

        // ==============================
        // 8. Ternary (Conditional) Operator
        // ==============================
        int max = (a > b) ? a : b;

        System.out.println("\nTernary Operator:");
        System.out.println("Max value = " + max);

        // ==============================
        // 9. instanceof Operator
        // ==============================
        String str = "Hello Java";

        System.out.println("\ninstanceof Operator:");
        System.out.println("str instanceof String : " + (str instanceof String));
        System.out.println("str instanceof Object : " + (str instanceof Object));
    }
}