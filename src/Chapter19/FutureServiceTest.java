package Chapter19;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: FutureServiceTest.java
 * @Description: 测试
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class FutureServiceTest {

	public static void main(final String[] args) throws InterruptedException {
//		test1();
//		test2();
		test3();
	}

	public static void test1() {
		// 定义不需要返回值的 service
		final FutureService<Void, Void> service = FutureService.newService();
		// submit 方法为立即返回的方法
		final Future<?> future = service.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I am finish done.");
		});
		// get 方法会使当前的线程进入阻塞
		try {
			future.get();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		// 定义有返回值的FutureService
		final FutureService<String, Integer> service = FutureService.newService();
		// submit 方法会立即返回
		final Future<Integer> future = service.submit(input -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			return input.length();
		}, "Hello");
		// get 方法使当前线程进入阻塞，最终会返回计算的结果
		try {
			System.out.println(future.get());
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test3() {
		final FutureService<String, Integer> service = FutureService.newService();
		service.submit(input -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			return input.length();
		}, "Hello", System.out::println);
	}
}
