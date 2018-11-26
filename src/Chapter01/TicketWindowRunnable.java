package Chapter01;

/**
 * **********************************************************************
 * 
 * @Title: TicketWindowRunnable.java
 * @Description: 优化之后的线程共享叫号方式
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public class TicketWindowRunnable implements Runnable {

	private int index = 1; // 不做static修饰

	private final static int MAX = 50;

	@Override
	public void run() {

		while (index <= MAX) {
			System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
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
