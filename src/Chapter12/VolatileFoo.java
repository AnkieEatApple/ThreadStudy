package Chapter12;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {

	// init_value 的最大值
	final static int MAX = 5;

	// init_value 的初始值
	static volatile int init_value = 0;

	public static void main(final String[] args) {

		// 启动一个Reader线程，当发现 local_value 和 init_value 不同时，则输出 init_value 被修改的信息
		new Thread(() -> {
			int localValue = init_value;
			while (localValue < MAX) {
				if (init_value != localValue) {
					System.out.println("The init_value update to [" + init_value + "]");

					// 对localValue进行重新赋值
					localValue = init_value;
				}
			}
		}, "Reader").start();

		// 启动 Updater 线程，主要用于对 init_value 的修改，当local_value >= 5 的时候则退出声明周期
		new Thread(() -> {
			int localValue = init_value;
			while (localValue < MAX) {
				// 修改init_value
				System.out.println("The init_value will be changed to [" + ++localValue + "]");
				init_value = localValue;
				try {
					// 短暂休眠，目的时为了使Reader 线程能够来得及输出变化内容
					TimeUnit.SECONDS.sleep(2);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Update").start();
	}
}
