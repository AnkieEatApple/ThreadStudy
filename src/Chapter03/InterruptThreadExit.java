package Chapter03;

import java.util.concurrent.TimeUnit;

public class InterruptThreadExit {

	public static void main(final String[] args) {
		final Thread thread = new Thread() {

			@Override
			public void run() {
				System.out.println("I will start work");
				while (!isInterrupted()) {
					// working
				}
				System.out.println("I will be exiting");
			}
		};

		thread.start();
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("System will be shut down.");
		thread.interrupt();
	}
}
