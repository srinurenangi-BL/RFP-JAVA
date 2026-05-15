package Day8.Line_Comparison_Problem_using_OOPS_Concept;

import java.util.logging.Logger;
import java.util.logging.Level;

public class LineComparisonComputation {
    private static final Logger logger = Logger.getLogger(LineComparisonComputation.class.getName());

    public static void main(String[] args) {
        // Master Branch Requirement: Welcome Message
        logger.info("Welcome to Line Comparison Computation Program on Master Branch");

        // Set up points for our lines
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(4.0, 6.0);
        Point p3 = new Point(1.0, 2.0);
        Point p4 = new Point(4.0, 6.0);
        Point p5 = new Point(0.0, 0.0);
        Point p6 = new Point(10.0, 10.0);

        // UC 1: Calculate Lengths
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p3, p4);
        Line line3 = new Line(p5, p6);

        logger.log(Level.INFO, "Length of Line 1: {0}", line1.getLength());
        logger.log(Level.INFO, "Length of Line 2: {0}", line2.getLength());
        logger.log(Level.INFO, "Length of Line 3: {0}", line3.getLength());

        // UC 2: Check Equality using equals()
        if (line1.equals(line2)) {
            logger.info("Result: Line 1 and Line 2 are EQUAL in length.");
        } else {
            logger.info("Result: Line 1 and Line 2 are NOT EQUAL in length.");
        }

        // UC 3: Compare Lines using compareTo()
        int comparisonResult = line1.compareTo(line3);
        if (comparisonResult > 0) {
            logger.info("Result: Line 1 is GREATER THAN Line 3.");
        } else if (comparisonResult < 0) {
            logger.info("Result: Line 1 is LESS THAN Line 3.");
        } else {
            logger.info("Result: Line 1 is EQUAL TO Line 3.");
        }
    }
}
