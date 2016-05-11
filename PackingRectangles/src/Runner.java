import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Runner {
	
	private Rectangle[] buildRectangles(ArrayList<Integer> data) {
	    int numberOfRectangles = data.size()/2;
	    Rectangle[] rectangles = new Rectangle[numberOfRectangles];
	    int j = 0;
	    for (int i = 0; i < numberOfRectangles; i++) {
	        Rectangle rect = new Rectangle(data.get(j++), data.get(j++));
	        rectangles[i] = rect;
	    }
	    return rectangles;
	}
	
	private void sortRectangles(Rectangle[] rectangles) {
		Comparator<Rectangle> rectSort = new Comparator<Rectangle>() {
			public int compare(Rectangle r1, Rectangle r2) {
				return r1.getVertObj().compareTo(r2.getVertObj());
			}
		};
		Arrays.sort(rectangles, rectSort);
		Collections.reverse(Arrays.asList(rectangles));	
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
		s.close();
	    return data;
	}
	
	static void runOptimized() throws IOException {
		Runner program = new Runner();
		ArrayList<Integer> data = program.parseData();
	    Rectangle[] rectangles = program.buildRectangles(data);
	    program.sortRectangles(rectangles);
	    int minArea = program.minAreaCalculator(rectangles);
		int maxHoriz = 0;
		int maxVert = 0;
	    maxVert = rectangles[0].getVert();
	    for(int i = 0; i < rectangles.length; i++) {
	    	maxHoriz = Math.max(maxHoriz, rectangles[i].getHoriz());
	    }
	    while (true) {
	    	ArrayList<Factor> factors = program.factorCalculator(minArea);
	    	CheckOptimizedFit fit = new CheckOptimizedFit(rectangles, factors, maxHoriz, maxVert);
	        fit.checkRectangleFit();
	        minArea++;
	    }
    }
	
	static void runExact() throws IOException {
		Runner program = new Runner();
		ArrayList<Integer> data = program.parseData();
	    Rectangle[] rectangles = program.buildRectangles(data);
	    int minArea = program.minAreaCalculator(rectangles);
	    while (true) {
	    	ArrayList<Factor> factors = program.factorCalculator(minArea);
			CheckExactFit fit = new CheckExactFit(rectangles, factors);
	        fit.checkRectangleFit();
	        minArea++;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		Scanner kbd = new Scanner(System.in);
		
		System.out.print("Enter 1 to run Exact Solution, 2 to run Optimized Solution:");
		if (kbd.nextInt() == 1) {
			runExact();
		} else {
			runOptimized();
		}
	}
}