package Chapter07;

import java.util.concurrent.TimeUnit;

public class EmptyExceptionHandler {

	public static void main(final String[] args) {

		// get current thread's thread group
		final ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.getName());
		System.out.println(mainGroup.getParent());
		System.out.println(mainGroup.getParent().getParent());

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}

			// here will throw unchecked exception
			System.out.println(1 / 0);
		}, "Test-Thread").start();
	}
}
