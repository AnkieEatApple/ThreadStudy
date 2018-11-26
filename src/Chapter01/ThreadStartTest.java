package Chapter01;

import java.util.concurrent.TimeUnit;

/**
 * **********************************************************************
 * 
 * @Title: ThreadStartTest.java
 * @Description: 线程测试
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public class ThreadStartTest {
	public static void main(final String[] args) {

		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("hello");
					TimeUnit.SECONDS.sleep(1);

				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();
//
//		// 休眠主要是为了确保thread结束声明周期
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (final InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// 企图重新激活该线程
//		thread.start();
		System.out.println("end");
	};
}
