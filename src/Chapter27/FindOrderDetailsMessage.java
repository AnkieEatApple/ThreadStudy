package Chapter27;

import java.util.Map;

import Chapter19.Future;

/**
 ********************************************************************** 
 * @Title: FindOrderDetailsMessage.java
 * @Description:
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class FindOrderDetailsMessage extends MethodMessage {

	// 构造方法
	public FindOrderDetailsMessage(final Map<String, Object> params, final OrderService orderService) {
		super(params, orderService);
	}

	@Override
	public void execute() {

		// 获取orderService的findOrderDetails方法
		final Future<String> readlFuture = orderService.findOrderDetials((Long) params.get("orderId"));
		// 获取Activefuture的引用
		final ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");

		try {
			// 调用orderServiceImpl返回的Future.get()，此方法会导致阻塞知道findOrderDetails方法完全执行结束
			final String result = readlFuture.get();
			activeFuture.finish(result);
		} catch (final InterruptedException e) {
			activeFuture.finish(null);
		}
	}
}
