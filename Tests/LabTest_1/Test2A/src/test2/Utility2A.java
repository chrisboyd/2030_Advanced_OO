package test2;

import java.util.List;

public class Utility2A {
	
	private Utility2A() {
		
	}
	
	public static Integer last(List<Integer> t) {
		return t.get(t.size() - 1);
	}
	
	public static int totalArea(List<Integer> widths, List<Integer> heights) {
		if (widths.size() != heights.size())
			throw new IllegalArgumentException("Widths and Heights lists are different sizes");
		
		int area = 0;
		for (int i = 0; i < widths.size(); i++) {
			if (widths.get(i) > 0 && heights.get(i) > 0)
				area += widths.get(i) * heights.get(i);
		}
		return area;
	}
	
	public static int alternatingSum(List<Integer> t) {
		int sum = 0;
		for (int i = 0; i < t.size(); i++) 
			sum += t.get(i) * Math.pow(-1, i);
		
		return sum;
	}
}
