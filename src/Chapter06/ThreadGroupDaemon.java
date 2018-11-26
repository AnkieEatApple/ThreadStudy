package Chapter06;

import java.util.concurrent.TimeUnit;

public class ThreadGroupDaemon {

	public static void main(final String[] args) throws InterruptedException {

		final ThreadGroup group1 = new ThreadGroup("Group1");
		new Thread(group1, () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}, "group1-thread1").start();

		final ThreadGroup group2 = new ThreadGroup("Group2");
		new Thread(group2, () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}, "group2-thread1").start();

		// 将 group2 设置为 daemon true
		group2.setDaemon(true);

		TimeUnit.SECONDS.sleep(3);
		System.out.println(group1.isDestroyed());
		System.out.println(group2.isDestroyed());

		final ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.getName());
		mainGroup.list();
	}
}
