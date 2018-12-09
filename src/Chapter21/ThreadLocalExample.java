package Chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 ********************************************************************** 
 * @Title: ThreadLocalExample.java
 * @Description: ThreadLocal 简单测试
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class ThreadLocalExample {

	public static void main(final String[] args) {
		//
		final ThreadLocal<Integer> tLocal = new ThreadLocal<>();
		//
		IntStream.range(0, 10).forEach(i -> new Thread(() -> {
			try {
				//
				tLocal.set(i);
				System.out.println(Thread.currentThread() + " set i " + tLocal.get());
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread() + " get i " + tLocal.get());
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}).start());
	}
}
