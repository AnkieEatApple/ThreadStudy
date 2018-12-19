package Chapter28;

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
		final Bus bus = new EventBus("TestBus");
		bus.register(new SimpleSubscriber1());
		bus.register(new SimpleSubscriber2());
		bus.post("Hello");
		System.out.println("-----------");
		bus.post("Hello", "test");

//		final Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
//		bus.register(new SimpleSubscriber1());
//		bus.register(new SimpleSubscriber2());
//		bus.post("Hello");
//		System.out.println("---------------");
//		bus.post("Hello", "test");
	}
}
