package test2;

import java.util.List;

public class testUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Character> l = Utility2C.repeatedChars("hi") ;
		
		for (char i : l) {
			System.out.println(i);
		}
		
		l = Utility2C.repeatedChars("EECS") ;
		
		for (char i : l) {
			System.out.println(i);
		}
		
		l = Utility2C.repeatedChars("EECS2030") ;
		
		for (char i : l) {
			System.out.println(i);
		}

	}

}
