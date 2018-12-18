package Chapter28;

import java.util.concurrent.ThreadPoolExecutor;

import org.w3c.dom.events.EventException;

/**
 ********************************************************************** 
 * @Title: AsyncEventBus.java
 * @Description: 将Thread-Per-Message用异步处理任务的Executer替换EventBus中的同步Executor
 * @author ankie
 * @date 2018年12月18日
 * @version 1.0
 **********************************************************************
 */
public class AsyncEventBus extends EventBus {

	// 构造方法-对外
	public AsyncEventBus(final String busName, final ThreadPoolExecutor executor) {
		this(busName, null, executor);
	}

	// 构造方法-对外
	public AsnycEventBus(final ThreadPoolExecutor executor) {
		this("default-async", null, executor);
	}

	// 构造方法-对外
	public AsyncEventBus(final EventExceptionHandler exceptionHandler, final ThreadPoolExecutor executor) {
		this("default-async", exceptionHandler, exceptionHandler);
	}

	// 构造方法-本地
	AsyncEventBus(final String busName, final EventExceptionHandler exceptionHandler,
			final ThreadPoolExecutor executor) {
		super(busName, exceptionHandler, executor);
	}
}
