package Chapter27;

/**
 ********************************************************************** 
 * @Title: OrderServiceFactory.java
 * @Description: 工厂类，但是只能生成一个关于Queue的OrderService代理
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class OrderServiceFactory {

	// 将ActiveMessageQueue定义成static的目的是，保持整个JVM进程中是唯一的，并且ActiveDaemonThread会在此刻启动
	private final static ActiveMessageQueue ACTIVE_MESSAGE_QUEUE = new ActiveMessageQueue();

	// 不允许外部通过new构造该类
	private OrderServiceFactory() {
	}

	public static OrderService toActiveObject(final OrderService orderService) {
		return new OrderServiceProxy(orderService, ACTIVE_MESSAGE_QUEUE);
	}

	public static void main(final String[] args) throws InterruptedException {
		// 在创建OrderService的时候需要传递OrderService接口的具体实现
		final OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
		orderService.order("hello", 453453);
		// 立即返回
		System.out.println("Return immediately");
		Thread.currentThread().join();
	}
}
