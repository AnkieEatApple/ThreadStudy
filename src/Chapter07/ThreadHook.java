package Chapter07;

import java.util.concurrent.TimeUnit;

public class ThreadHook {

	public static void main(final String[] args) {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				super.run();
				System.out.println("The hook thread 1 is running.");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 1 will exit.");
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				super.run();

				System.out.println("The hook thread 2 is running.");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 2 is exit.");
			}
		});

		// 最先执行的语句
		System.out.println("The program will is stopping.");
	}
}
