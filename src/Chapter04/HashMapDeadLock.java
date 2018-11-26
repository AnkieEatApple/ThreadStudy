package Chapter04;

import java.util.HashMap;

public class HashMapDeadLock {

	private final HashMap<String, String> map = new HashMap<>();

	public void add(final String key, final String value) {
		this.map.put(key, value);
	}

	public static void main(final String[] args) {

		final HashMapDeadLock had1 = new HashMapDeadLock();

		for (int x = 0; x < 2; x++) {
			new Thread(() -> {
				for (int i = 1; i < Integer.MAX_VALUE; i++) {
					had1.add(String.valueOf(i), String.valueOf(i));
				}
			}, "线程啊").start();
		}
	}
}
