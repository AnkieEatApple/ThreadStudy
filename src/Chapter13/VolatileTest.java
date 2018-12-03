package Chapter13;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {

	// 使用 volatile 修饰共享资源 i
	private static volatile int i = 0;

	// 使用在第23章中开发的 CountDownLatch
	private static final CountDownLatch latch = new CountDownLatch(10);

	private static void inc() {
		// synchronized (latch) {
		i++;
		// }
		// 如果对i 字段不进行synchronized 的话，i 的写入会造成原子性重复
		// 导致最后的结果并不是 10000
	}

	public static void main(final String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int x = 0; x < 1000; x++) {
					inc();
				}

				// 使计数器减1
				latch.countDown();
			}).start();
		}
		// 等待所有的线程完成工作
		try {
			latch.await();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}
}
