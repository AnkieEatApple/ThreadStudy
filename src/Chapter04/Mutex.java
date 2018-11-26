package Chapter04;

import java.util.concurrent.TimeUnit;

public class Mutex {

	private final static Object MUTEX = new Object();

	private final static String name[] = { "线程1", "线程2", "线程3", "线程4", "线程5" };

	private void accessResource() {
		synchronized (MUTEX) {
			try {
				TimeUnit.MINUTES.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(final String[] args) {
		final Mutex mutex = new Mutex();
		for (int i = 0; i < 5; i++) {
			new Thread(mutex::accessResource, name[i]).start();
		}
	}
}
