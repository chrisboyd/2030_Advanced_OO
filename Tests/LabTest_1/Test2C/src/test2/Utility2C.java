package test2;

import java.util.ArrayList;
import java.util.List;

public class Utility2C {
	
	private Utility2C() {
		
	}
	
	public static char last(String s) {
		return s.charAt(s.length() - 1);
	}
	
	public static boolean areReversed(String s, String t) {
		if (s.length() != t.length())
			throw new IllegalArgumentException("Strings not same length");
		
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(t.length() - i - 1))
				return false;
		}
		return true;
	}
	
	public static List<Character> repeatedChars(String s){
		List<Character> rep = new ArrayList<Character>();
		
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.substring(i+1).contains(s.subSequence(i, i+1)) )
				if (!rep.contains(s.charAt(i)))
					rep.add(s.charAt(i));
		}
		return rep;
	}

	
}
