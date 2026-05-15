package Day8.Line_Comparison_Problem_using_OOPS_Concept;

import java.util.Objects;

public class Line implements Comparable<Line> {
    private final Point startPoint;
    private final Point endPoint;
    private final Double length;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = calculateLength();
    }

    private Double calculateLength() {
        double xDelta = endPoint.getX() - startPoint.getX();
        double yDelta = endPoint.getY() - startPoint.getY();
        return Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
    }

    public Double getLength() {
        return length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Line otherLine = (Line) obj;
        return Objects.equals(this.length, otherLine.length);
    }

    @Override
    public int compareTo(Line otherLine) {
        return this.length.compareTo(otherLine.length);
    }
}