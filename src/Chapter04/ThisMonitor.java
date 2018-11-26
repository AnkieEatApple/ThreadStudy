package Chapter04;

import java.util.concurrent.TimeUnit;

public class ThisMonitor {

	public synchronized void method1() {
		System.out.println(Thread.currentThread().getName() + " enter to method1");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void method2() {
		System.out.println(Thread.currentThread().getName() + " enter to method2");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		final ThisMonitor monitor = new ThisMonitor();
		new Thread(monitor::method1, "线程1").start();
		new Thread(monitor::method2, "线程2").start();
	}
}
