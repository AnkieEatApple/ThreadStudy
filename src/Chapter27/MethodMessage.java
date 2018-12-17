package Chapter27;

import java.util.Map;

/**
 ********************************************************************** 
 * @Title: MethodMessage.java
 * @Description: 收集每一个接口的方法参数，并提供execute方法提供ActiveDaemonThread直接调用
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public abstract class MethodMessage {

	// 用于收集方法参数，如果又返回Future类型则一并收集
	protected final Map<String, Object> params;

	// olderService对象
	protected final OrderService orderService;

	// 构造方法
	public MethodMessage(final Map<String, Object> params, final OrderService orderService) {
		this.params = params;
		this.orderService = orderService;
	}

	// 抽象方法，扮演work thread的说明书
	public abstract void execute();

}
