package Chapter04;

public class TicketWindowRunnable implements Runnable {

	private volatile int index = 1;

	private final static int MAX = 500;

	private final Object MUTEX = new Object();

	@Override
	public void run() {
		// 添加 synchronized 就好了
		synchronized (MUTEX) {
			while (index <= MAX) {
				System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
			}
		}

	}

	public static void main(final String[] args) {
		final TicketWindowRunnable task = new TicketWindowRunnable();

		final Thread windowThread1 = new Thread(task, "一号窗口");
		final Thread windowThread2 = new Thread(task, "二号窗口");
		final Thread windowThread3 = new Thread(task, "三号窗口");
		final Thread windowThread4 = new Thread(task, "四号窗口");

		windowThread1.start();
		windowThread2.start();
		windowThread3.start();
		windowThread4.start();
	}
}
