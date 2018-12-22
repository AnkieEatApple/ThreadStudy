package Chapter29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 ********************************************************************** 
 * @Title: AsyncChannel.java
 * @Description:
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public abstract class AsyncChannel implements Channel<Event> {

	private final ExecutorService executorService;

	// 默认的构造函数
	public AsyncChannel() {
		// 卧槽，这个高端，Runtime.getRuntime().availableProcessors() * 2 当前CPUx2
		this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
	}

	// 用户自定义的ExecutorService
	public AsyncChannel(final ExecutorService executorService) {
		this.executorService = executorService;
	}

	@Override
	public void dispatch(final Event message) {
		executorService.submit(() -> this.handle(message));
	}

	// 提供抽象方法，提供子类实现具体的Message处理
	protected abstract void handle(Event message);

	// 提供关闭ExecutorService的方法
	void stop() {
		if (null != executorService && !executorService.isShutdown()) {
			executorService.shutdown();
		}
	}
}
