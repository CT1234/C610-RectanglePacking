import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
	
	private Rectangle[] buildRectangles(ArrayList<Integer> data) {
	    int numberOfRectangles = data.get(0);
	    Rectangle[] rectangles = new Rectangle[numberOfRectangles];
	    int textPos = 1;
	    
	    for (int i = 0; i < numberOfRectangles; i++) {
	        Rectangle rect = new Rectangle(data.get(textPos++), data.get(textPos++));
	        rectangles[i] = rect;
	    }
	    return rectangles;
	}
	
	private ArrayList<Factor> factorCalculator(int area) {
		ArrayList<Factor> allFactors = new ArrayList<Factor>();
	    for (int i = 1; i <= area; i++) {
	        if (area % i == 0) {
	            Factor factor = new Factor(i, area/i);
	            allFactors.add(factor);
	        }
	    }
	    return allFactors;
	}
	
	private int minAreaCalculator(Rectangle[] rectangles) {
	    int areaSum = 0;
	    for (int i = 0; i < rectangles.length; i++) {
	        areaSum += rectangles[i].getArea();
	    }
	    return areaSum;
	}
	
	private ArrayList<Integer> parseData() throws IOException {
		Scanner s = new Scanner(Paths.get("instance.txt"));
		ArrayList<Integer> data = new ArrayList<Integer>();
		while (s.hasNext()) {
			data.add(s.nextInt());
		}
	    return data;
	}
	
	public static void main(String[] args) throws IOException {
		
		Runner program = new Runner();
		ArrayList<Integer> data = program.parseData();
	    Rectangle[] rectangles = program.buildRectangles(data);
	    int minArea = program.minAreaCalculator(rectangles);
	    
	    boolean fitFound = false;
	    
	    while (!fitFound) {
	    	ArrayList<Factor> factors = program.factorCalculator(minArea);
			CheckFit fit = new CheckFit(rectangles, factors);
	        fitFound = fit.checkRectangleFit();
	        System.out.println(minArea);
	        minArea++;
	    }
	    System.out.print(minArea--);
	}
}