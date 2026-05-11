package Day3;

public class LineComparisonUC3 {
	 public static void main(String[] args) {

	        Line line1 = new Line(0, 0, 2, 2);
	        Line line2 = new Line(0, 0, 4, 4);

	        int result = line1.compareTo(line2);

	        if (result == 0)
	            System.out.println("Both lines are Equal");
	        else if (result < 0)
	            System.out.println("Line1 is Shorter than Line2");
	        else
	            System.out.println("Line1 is Longer than Line2");
	    }

}
