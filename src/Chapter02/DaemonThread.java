package Chapter02;

public class DaemonThread {

	public static void main(final String[] args) {
		// (1) main线程开始
		final Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1); // ????
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// (2) 将thread线程设置为守护线程
		thread.setDaemon(true);

		// (3) 启动thread线程
		thread.start();
		try {
			Thread.sleep(2_000L);

		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main thread finished lifecycle.");

		// (4) 线程结束
//		System.exit(0);
//		System.out.println("ll");

	}

}
