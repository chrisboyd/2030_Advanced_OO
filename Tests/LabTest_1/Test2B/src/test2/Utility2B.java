package test2;


public class Utility2B {
	
	private Utility2B() {
		
	}
	
	public static String join(String s, String t, String sep) {
		return s.concat(sep).concat(t);
	}
	
	public static int distance(String s, String t) {
		if (s.length() != t.length())
			throw new IllegalArgumentException("strings different length");
		
		int diff = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i))
				diff++;
		}
		return diff;
	}
	
	public static boolean isPalindrome(String s) {
		int j = s.length();
		if (j > 1) {
			for (int i = 0; i <= j; i++) {
				if (s.charAt(i) != s.charAt((j - 1)))
					return false;
				j--;
			}
		}
		return true;
	}
	
}
