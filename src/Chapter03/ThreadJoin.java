package Chapter03;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * **********************************************************************
 * 
 * @Title: ThreadJoin.java
 * @Description: 线程 join
 * @author ankie
 * @date 2018年11月16日
 * @version 1.0
 **********************************************************************
 */
public class ThreadJoin {

	public static void main(final String[] args) throws InterruptedException {
		// (1) 定义两个线程，并保存在 threads 中
		final List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(toList());

		// (2) 启动这两个线程
		threads.forEach(Thread::start);

		// (3) 执行者两个线程的 join 方法
		// for (final Thread thread : threads) {
		// thread.join();
		// }

		// (4) main线程循环输出
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "#" + i);
			shortSleep();
		}
	}

	/**
	 * 构造一个简单的线程，每个线程只是简单的循环输出
	 * 
	 * @param seq 线程名字
	 * @return 线程
	 */
	private static Thread create(final int seq) {
		final String name[] = { "thread one", "thread two" };

		return new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " # " + i);
				shortSleep();
			}
		}, String.valueOf(name[(int) (Math.random() * 2)]));
	}

	private static void shortSleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
