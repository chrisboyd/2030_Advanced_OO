package test2;

import java.util.ArrayList;
import java.util.List;

public class Utility2D {

	private Utility2D() {

	}

	public static double reciprocal(int x) {
		if (x == 0)
			throw new ArithmeticException("can't reciprocal 0");

		return 1.0 / x;
	}

	public static List<Integer> interval(int a, int b) {
		List<Integer> order = new ArrayList<Integer>();

		for (int s = Math.min(a, b); s <= Math.max(a, b); s++)
			order.add(s);

		return order;
	}
	
	public static List<Integer> zipper(List<Integer> s, List<Integer> t){
		List<Integer> r = new ArrayList<Integer>();
		
		for (int i = 0; i < Math.max(s.size(), t.size()); i++) {
			if(i < s.size())
				r.add(s.get(i));
			if(i < t.size())
				r.add(t.get(i));
		}
		
		return r;
		
	}

}
