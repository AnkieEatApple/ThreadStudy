package Chapter28;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 ********************************************************************** 
 * @Title: EventBusTest.java
 * @Description: EventBus测试
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public class EventBusTest {
	public static void main(final String[] args) {
		final String string = "ll";
		try {
			final Class<?> aClass = Class.forName("Chapter28.SimpleSubscriber1");
			final Method method = aClass.getDeclaredMethod("method1", String.class);
			final Object object = aClass.newInstance();
			method.invoke(object, "hello ankie");
			final Class<?> as = method.getParameterTypes()[0];
			System.out.println(as.isAssignableFrom(string.getClass()));
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final Bus bus1 = new EventBus("TestBus");
		bus1.register(new SimpleSubscriber1());
		bus1.register(new SimpleSubscriber1());
		bus1.register(new SimpleSubscriber2());
		bus1.post("Hello");
		System.out.println("-----------");
		bus1.post("Hello world", "test");
		System.out.println("-------------------------------------------");

		final Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
		bus.register(new SimpleSubscriber1());
		bus.register(new SimpleSubscriber2());
		bus.post("nihao");
		System.out.println("---------------");
		bus.post("nihao Hello world", "test");
	}
}
