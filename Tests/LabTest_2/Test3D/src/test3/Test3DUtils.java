package test3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test3DUtils {

	private Test3DUtils() {
		
	}
	
	public static void initialize(CallLog clog) throws Exception {
		try {
			String n = "Default name";
			List<CallLogEntry> c = new ArrayList<>();
			Field name = CallLog.class.getDeclaredField("name");
			name.setAccessible(true);
			name.set(clog, n);
			Field log = CallLog.class.getDeclaredField("log");
			log.setAccessible(true);
			log.set(clog, c);			
		}
		catch (Exception x) {
			System.out.println(x);
			throw x;
		}
	}
}
