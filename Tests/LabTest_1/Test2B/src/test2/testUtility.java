package test2;

public class testUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "level";
		String s2 = "noon";
		String s3 = "racecar";
		String s4 = "not";
		String s5 = "s";
		
		System.out.println("true: " + Utility2B.isPalindrome(s1));
		System.out.println("true: " + Utility2B.isPalindrome(s2));
		System.out.println("true: " + Utility2B.isPalindrome(s3));
		System.out.println("false: " + Utility2B.isPalindrome(s4));
		System.out.println("true: " + Utility2B.isPalindrome(s5));
		
		System.out.println("distance: " + Utility2B.distance("talk", "talk"));
		System.out.println("distance: " + Utility2B.distance("talk", "walk"));
		System.out.println("distance: " + Utility2B.distance("well", "walk"));
		System.out.println("distance: " + Utility2B.distance("pick", "walk"));
		System.out.println("distance: " + Utility2B.distance("zzzz", "walk") );
		

	}

}
