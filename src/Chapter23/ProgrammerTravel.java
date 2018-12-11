package Chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProgrammerTravel extends Thread {

	// 门阀
	private final Latch latch;
	// 程序猿
	private final String programmer;
	// 交通工具
	private final String transportation;

	// 构造方法
	public ProgrammerTravel(final Latch latch, final String programmer, final String transportation) {
		this.latch = latch;
		this.programmer = programmer;
		this.transportation = transportation;
	}

	@Override
	public void run() {
		System.out.println(programmer + "\t start take the transportation [" + transportation + "]");
		try {
			// 程序猿乘坐交通工具花费在路上的时间(使用随机数字模拟)
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(programmer + " arrived by " + transportation);
		// 完成任务时使计数器 -1
		latch.countDown();
	}

	public static void main(final String[] args) throws InterruptedException {
		// 定义Latch， limit为4
		final Latch latch = new CountDownLatch(4);
		new ProgrammerTravel(latch, "Alex", "Bus").start();
		new ProgrammerTravel(latch, "Gavin", "Walking").start();
		new ProgrammerTravel(latch, "Jack", "Subway").start();
		new ProgrammerTravel(latch, "Dillon", "Bicycle").start();
		// 当前线程， main 线程会进入阻塞，直到四个程序猿全部都会到达目的地
		try {
			latch.await(TimeUnit.SECONDS, 5);
			System.out.println("== all of programmer arrived ==");
		} catch (final WaitTimeoutException e) {
			e.printStackTrace();
		}
		System.out.println("====all of programmer====");
	}
}
