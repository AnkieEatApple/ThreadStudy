package Chapter27;

import java.util.HashMap;
import java.util.Map;

import Chapter19.Future;

/**
 ********************************************************************** 
 * @Title: OrderServiceProxy.java
 * @Description: OrderService的代理，将OrderService的每一个方法封装成MethodMessage，提交给ActiveMessage队列
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class OrderServiceProxy implements OrderService {

	// OldService的引用
	private final OrderService orderService;

	// ActiveMessageQueue的引用
	private final ActiveMessageQueue activeMessageQueue;

	// 构造方法
	public OrderServiceProxy(final OrderService orderService, final ActiveMessageQueue activeMessageQueue) {
		this.orderService = orderService;
		this.activeMessageQueue = activeMessageQueue;
	}

	@Override
	public Future<String> findOrderDetials(final long orderId) {
		// 定义一个ActiveFuture，并且可以支持立即返回
		final ActiveFuture<String> activeFuture = new ActiveFuture<>();
		// 收集方法入参以及返回的ActiveFuture封装成MethodMessage
		final Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("activeFuture", activeFuture);
		final MethodMessage message = new FindOrderDetailsMessage(params, orderService);
		// 将MethodMessage 保存至 activeMessageQueue中
		// activeMessageQueue.offer(message);
		return activeFuture;
	}

	@Override
	public void order(final String account, final long orderId) {
		// 收集方法参数，并且封装成MethodMessage，然后offer到队列中
		final Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("orderId", orderId);
		final MethodMessage message = new OrderMessage(params, orderService);
		// activeMessageQueue.offer(message);
	}
}
