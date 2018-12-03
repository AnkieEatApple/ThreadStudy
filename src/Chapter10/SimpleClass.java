package Chapter10;

import java.util.ArrayList;
import java.util.List;

public class SimpleClass {

	// 在 SimpleClass 中使用 byte[]
	private static byte[] buffer = new byte[8];

	// 在SimpleClass 中使用String
	private static String string = "";

	// 在SimpleClass中使用List
	private static List<String> list = new ArrayList<String>();

	static {
		buffer[0] = (byte) 1;
		string = "Simple";
		list.add("element");
		System.out.println(buffer[0]);
		System.out.println(string);
		System.out.println(list.get(0));
	}

}
