package JavaDay3PracticeProblems;

public class Line implements Comparable<Line>{
	
	private final int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double length() {
        return Math.sqrt(
                Math.pow(x2 - x1, 2) +
                Math.pow(y2 - y1, 2)
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Line)) return false;

        Line other = (Line) obj;
        return Double.compare(this.length(), other.length()) == 0;
    }

    @Override
    public int compareTo(Line other) {
        return Double.compare(this.length(), other.length());
    }

}
