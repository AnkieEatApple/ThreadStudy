package Chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadisInterrupted {

	public static void main(final String[] args) {
		final Thread thread = new Thread() {

			@Override
			public void run() {
				while (true) {
					// do nothing, just empty loop
				}
			}
		};

		thread.start();
		try {
			TimeUnit.MICROSECONDS.sleep(2);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread is interrupted ? " + thread.isInterrupted());
		thread.interrupt();
		System.out.println("Thread is interrupted ? " + thread.isInterrupted());
	}
}
