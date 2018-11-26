package Chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {

	public static void main(final String[] args) {
//		final Thread thread = new Thread() {
//			@Override
//			public void run() {
//				while (true) {
//					System.out.println(Thread.interrupted());
//				}
//			}
//		};
//
//		thread.setDaemon(true);
//		thread.start();
//
//		// shortly block make sure the thread is started
//		try {
//			TimeUnit.MICROSECONDS.sleep(2);
//		} catch (final InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		thread.interrupt();

		// (1) 判断当前线程是否被中断
		System.out.println("Main thread is interrupted? " + Thread.interrupted());

		// (2) 中断当前线程
		Thread.currentThread().interrupt();

		// (3) 判断当前线程是否已经被中断
		System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());

		try {
			// (4) 当前线程执行可中断方法
			TimeUnit.MINUTES.sleep(1);
		} catch (final InterruptedException e) {
			// (5) 捕获中断信号
			System.out.println("I will be interrupted still.");
		}
	}
}
