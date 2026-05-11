package JavaDay3PracticeProblems;

public class LineEqualityUC2 {
	public static void main(String[] args) {

        Line line1 = new Line(0, 0, 4, 3);
        Line line2 = new Line(0, 0, 4, 3);

        boolean isEqual = line1.equals(line2);
        System.out.println("Lines are Equal: " + isEqual);
    }

}
