package Chapter15;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: ObservableThreadTest.java
 * @Description:
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
public class ObservableThreadTest {

	public static void main(final String[] args) {

		//
		final Observable observableThread = new ObservableThread<>(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" finish done.");
			return null;
		});

		observableThread.start();
	}
}
