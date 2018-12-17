package Chapter27;

import java.util.Map;

/**
 ********************************************************************** 
 * @Title: OrderMessage.java
 * @Description: 主要处理Order的方法，从param中获取接口参数，然后执行真正的OrderService的方法
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class OrderMessage extends MethodMessage {

	public OrderMessage(final Map<String, Object> params, final OrderService orderService) {
		super(params, orderService);
	}

	@Override
	public void execute() {
		// 获取参数
		final String account = (String) params.get("account");
		final long orderId = (long) params.get("orderId");

		// 执行真正的order方法
		orderService.order(account, orderId);
	}
}
