package Chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {

	public static void main(final String[] args) throws InterruptedException {

		final Thread thread = new Thread(() -> {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				System.out.println("Oh, i am be interrupted.");
			}

		});
		thread.start();

		// short block and make sure thread is started.
		TimeUnit.MICROSECONDS.sleep(2);
		thread.interrupt();
	}
}
