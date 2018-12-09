package Chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 ********************************************************************** 
 * @Title: FutureServiceImpl.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

	// 为执行的线程指定名字的前缀，**再三强调，为线程起一个特殊的名字是一个非常好的编程习惯
	private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

	private final AtomicInteger nextCounter = new AtomicInteger(0);

	private String getNextName() {
		return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
	}

	@Override
	public Future<?> submit(final Runnable runnable) {
		final FutureTask<Void> future = new FutureTask<>();
		new Thread(() -> {
			runnable.run();
			// 任务执行结束之后将null作为结果传给future
			future.finish(null);
		}, getNextName()).start();
		return future;
	}

	@Override
	public Future<OUT> submit(final Task<IN, OUT> task, final IN input) {

		// 创建的新的future
		final FutureTask<OUT> future = new FutureTask<>();
		new Thread(() -> {
			final OUT result = task.get(input);
			// 任务执行结束之后，将真实的结果通过finish 方法传递给future
			future.finish(result);
		}, getNextName()).start();

		return future;
	}

	@Override
	public Future<OUT> submit(final Task<IN, OUT> task, final IN input, final Callback<OUT> callback) {

		//
		final FutureTask<OUT> future = new FutureTask<>();
		new Thread(() -> {
			final OUT result = task.get(input);
			future.finish(result);
			//
			if (null != callback) {
				callback.call(result);
			}
		}, getNextName()).start();
		return future;
	}

}
