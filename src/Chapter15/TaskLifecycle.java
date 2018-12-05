package Chapter15;

/**
 ********************************************************************** 
 * @Title: TaskLifecycle.java
 * @Description:
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
public interface TaskLifecycle<T> {

	// 任务启动的时候会触发 onStart 方法
	void onStart(Thread thread);

	// 任务正在运行时会触发 onRunning 方法
	void onRunning(Thread theread);

	// 任务运行结束时会触发 onFinish 方法，其中 result 是任务执行结束后的结果
	void onFinish(Thread thread, T result);

	// 任务执行报错时会触发 onError 方法
	void onError(Thread thread, Exception e);

	// 生命周期的接口的全部实现 相当于 Adapter
	class EmptyLifecycle<T> implements TaskLifecycle<T> {

		@Override
		public void onStart(final Thread thread) {
			// TODO nothing
			System.out.println("thread on start");
		}

		@Override
		public void onRunning(final Thread theread) {
			// TODO nothing
			System.out.println("thread on running");
		}

		@Override
		public void onFinish(final Thread thread, final T result) {
			// TODO nothing
			System.out.println("thread on finish");
		}

		@Override
		public void onError(final Thread thread, final Exception e) {
			// TODO nothing
			System.out.println("thread on error");
		}
	}
}
