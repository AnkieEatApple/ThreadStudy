package Chapter27;

import Chapter19.Future;

/**
 ********************************************************************** 
 * @Title: OrderService.java
 * @Description: 订单服务接口
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public interface OrderService {
	/**
	 * 根据订单编号查询订单明细，有入参也有返回值，但是返回值类型必须是Future
	 * 
	 * @param orderId 订单编号
	 * @return Future类型
	 */
	Future<String> findOrderDetials(long orderId);

	/**
	 * 提交订单，没有返回值
	 * 
	 * @param account 证件
	 * @param orderId 订单号
	 */
	void order(String account, long orderId);
}