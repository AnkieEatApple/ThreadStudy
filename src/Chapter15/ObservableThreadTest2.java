package Chapter15;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: ObservableThreadTest2.java
 * @Description:
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
public class ObservableThreadTest2 {

	public static void main(final String[] args) {

		// 重写了原来的 onFinish 的方法
		final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
			@Override
			public void onFinish(final Thread thread, final String result) {
				System.out.println("The result is " + result);
			}
		};

		final Observable observableThread = new ObservableThread<>(lifecycle, () -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" finish done.");
			return "Hello Observable";
		});

		observableThread.start();
	}
}
