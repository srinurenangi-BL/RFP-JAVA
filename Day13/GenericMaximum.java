package Day13;

public class GenericMaximum<T extends Comparable<T>> {
    T x, y, z;

    // Parameterized Constructor
    public GenericMaximum(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T testMaximum() {
        return GenericMaximum.findMax(x, y, z);
    }

    public static <T extends Comparable<T>> T findMax(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) max = y;
        if (z.compareTo(max) > 0) max = z;
        return max;
    }
}
