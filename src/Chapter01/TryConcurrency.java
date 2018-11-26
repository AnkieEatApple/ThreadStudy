package Chapter01;

import java.util.concurrent.TimeUnit;

/**
 * **********************************************************************
 * 
 * @Title: TryConcurrency.java
 * @Description: 多线程了解
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public class TryConcurrency {

	public static void main(final String[] args) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				enjoyMusic();
			}
		}.start();

		new Thread(TryConcurrency::enjoyMusic).start();

		browsNews();

	}

	/**
	 * Listening and enjoy music
	 */
	private static void enjoyMusic() {
		for (;;) {
			System.out.println("Uh-huh, the nice music.");
			sleep(1);
		}

	}

	/**
	 * Browse the latest news.
	 */
	private static void browsNews() {
		for (;;) {
			System.out.println("Uh-huh, the good news.");
			sleep(1);
		}
	}

	/**
	 * Simulate the wait and ignore exception.
	 * 
	 * @param seconds
	 */
	private static void sleep(final int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
