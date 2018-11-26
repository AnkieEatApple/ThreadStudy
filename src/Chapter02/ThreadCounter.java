package Chapter02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter extends Thread {

	final static AtomicInteger counter = new AtomicInteger(0);

	public static void main(final String[] args) {
		try {
			while (true) {
				new ThreadCounter().start();
			}
		} catch (final Exception e) {
			System.out.println("failed At=> " + counter.get());
		}

	}

	@Override
	public void run() {
		super.run();

		try {
			System.out.println("The " + counter.getAndIncrement() + " thead be created.");
			TimeUnit.SECONDS.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
