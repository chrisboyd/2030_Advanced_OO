package test3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test3BUtils {

	private Test3BUtils() {
		
	}
	
	
	public static void initialize(CellPhone phone) throws Exception {
		try {
			CellNumber n = new CellNumber(416, 736, 2100);
			List<CellNumber> c = new ArrayList<>();
			Field number = CellPhone.class.getDeclaredField("number");
			number.setAccessible(true);
			number.set(phone, n);
			Field callLog = CellPhone.class.getDeclaredField("callLog");
			callLog.setAccessible(true);
			callLog.set(phone, c);
		}
		catch (Exception x) {
			System.out.println(x);
			throw x;
		}
	}
}
