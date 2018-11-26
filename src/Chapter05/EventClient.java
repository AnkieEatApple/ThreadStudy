package Chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {

	public static void main(final String[] args) {

		final EventQueue eventQueue = new EventQueue();

		new Thread(() -> {
			for (;;) {
				eventQueue.offer(new EventQueue.Event());
			}
		}, "Producer").start();

		new Thread(() -> {

			for (;;) {
				eventQueue.take();
				try {
					TimeUnit.MICROSECONDS.sleep(10);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Consumer").start();
	}
}
