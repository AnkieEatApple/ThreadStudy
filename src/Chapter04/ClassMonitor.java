package Chapter04;

import java.util.concurrent.TimeUnit;

public class ClassMonitor {

	public synchronized static void method1() {
		System.out.println(Thread.currentThread().getName() + " enter to method1");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void method2() {
		System.out.println(Thread.currentThread().getName() + " enter to method2");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		new Thread(ClassMonitor::method1).start();
		new Thread(ClassMonitor::method2).start();
	}
}
