package Chapter03;

public class ThreadSleep {

	public static void main(final String[] args) {
		new Thread(() -> {
			final long startTime = System.currentTimeMillis();
			sleep(2_000L);
			final long endTime = System.currentTimeMillis();
			System.out.println(String.format("Total spend %d ms", (endTime - startTime)));

		}).start();

		final long startTime = System.currentTimeMillis();
		sleep(3_000L);
		final long endTime = System.currentTimeMillis();
		System.out.println(String.format("Main thread toatl spend %d ms", (endTime - startTime)));
	}

	private static void sleep(final long ms) {
		try {
			Thread.sleep(ms);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// 结果:
//	Total spend 2004 ms
//	Main thread toatl spend 3005 m
