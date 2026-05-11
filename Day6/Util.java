package Day6;

public class Util {

    // 2. Day of Week
    public static int dayOfWeek(int m, int d, int y) {
        int y0 = y - (14 - m) / 12;
        int x = y0 + (y0 / 4) - (y0 / 100) + (y0 / 400);
        int m0 = m + 12 * ((14 - m) / 12) - 2;
        int d0 = (d + x + (31 * m0) / 12) % 7;
        return d0;
    }

    // 3. Temperature Conversion
    public static double temperatureConversion(double temp, String convertTo) {
        if (convertTo.equalsIgnoreCase("C")) {
            return (temp - 32) * 5 / 9; // F to C
        } else if (convertTo.equalsIgnoreCase("F")) {
            return (temp * 9 / 5) + 32; // C to F
        }
        return 0;
    }

    // 4. Monthly Payment Calculation
    public static double monthlyPayment(double P, double Y, double R) {
        double n = 12 * Y;
        double r = R / (12 * 100);
        double payment = (P * r) / (1 - Math.pow(1 + r, -n));
        return payment;
    }

    // 5. Square Root using Newton's Method
    public static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double t = c;
        double epsilon = 1e-15;
        while (Math.abs(t - c / t) > epsilon * t) {
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    // 6. Decimal to Binary (32-bit String padding)
    public static String toBinary(int n) {
        StringBuilder binary = new StringBuilder();
        int power = 1;
        // Find highest power of 2 less than or equal to n
        while (power <= n / 2) {
            power *= 2;
        }
        // Decompose into sum of powers
        while (power > 0) {
            if (n >= power) {
                binary.append("1");
                n -= power;
            } else {
                binary.append("0");
            }
            power /= 2;
        }
        
        // Pad to ensure 4 bytes (32 bits)
        String binStr = binary.toString();
        while (binStr.length() < 32) {
            binStr = "0" + binStr;
        }
        return binStr;
    }
}