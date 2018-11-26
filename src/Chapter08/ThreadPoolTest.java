package Chapter08;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: ThreadPoolTest.java
 * @Description: 线程池的实现的测试实例
 * @author ankie
 * @date 2018年11月26日
 * @version 1.0
 **********************************************************************
 */
public class ThreadPoolTest {

	public static void main(final String[] args) throws InterruptedException {

		// 定义线程池，初始化的线程数为2，核心线程数为4，最大线程数为6，任务队列最多允许1000个任务
		final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
		// 定义20个任务并且提交给线程池
		for (int i = 0; i < 20; i++) {
			threadPool.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(10);
					System.out.println(Thread.currentThread().getName() + " is running and done.");
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		for (;;) {
			// 不断的输出线程池的信息
			System.out.println("getActivityCount:" + threadPool.getActivityCount());
			System.out.println("getQueueSize:" + threadPool.getQueueSize());
			System.out.println("getCoreSize:" + threadPool.getCoreSize());
			System.out.println("getMaxSize:" + threadPool.getMaxSize());
			System.out.println("=============================================");
			TimeUnit.SECONDS.sleep(5);
		}
	}
}
