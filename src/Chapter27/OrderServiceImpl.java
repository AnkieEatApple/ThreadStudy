package Chapter27;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import Chapter19.Future;
import Chapter19.FutureService;

/**
 ********************************************************************** 
 * @Title: OrderServiceImpl.java
 * @Description: OrderService的接口实现
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class OrderServiceImpl implements OrderService {

	@ActiveMethod
	@Override
	public Future<String> findOrderDetials(final long orderId) {
		return FutureService.<Long, String>newService().submit(input -> {
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("process the orderID->" + orderId);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			return "The order Details Information";
		}, orderId, null);
	}

	@ActiveMethod
	@Override
	public void order(final String account, final long orderId) {
		IntStream.range(1, 10).forEach(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("sleep : " + i);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("process the order for acccount " + account + ",orderId: " + orderId);
	}
}
