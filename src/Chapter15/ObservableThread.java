package Chapter15;

/**
 ********************************************************************** 
 * @Title: ObservableThread.java
 * @Description: 任务监控的关键
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
public final class ObservableThread<T> extends Thread implements Observable {

	// 任务生命周期接口 引用对象
	private final TaskLifecycle<T> lifecycle;

	// 任务函数接口 引用对象
	private final Task<T> task;

	// 生命周期的枚举类型
	private Cycle cycle;

	// 指定 Task 的实现，默认的情况下使用 EmptyLifecycle
	public ObservableThread(final Task<T> task) {
		this(new TaskLifecycle.EmptyLifecycle<>(), task);
	}

	// 指定 TaskLifecycle 的同时指定 Task
	public ObservableThread(final TaskLifecycle<T> lifecycle, final Task<T> task) {
		super();
		// Task 不允许为 null
		if (null == task) {
			throw new IllegalArgumentException("The task is required.");
		}
		this.lifecycle = lifecycle;
		this.task = task;
	}

	@Override
	public final void run() {
		// 在执行线程逻辑单元的时候，分别触发相应的事件
		this.update(Cycle.STARTED, null, null);

		try {
			this.update(Cycle.RUNNING, null, null);
			final T result = this.task.call();
			this.update(Cycle.DONE, result, null);
		} catch (final Exception e) {
			this.update(Cycle.ERROR, null, e);
		}
	}

	private void update(final Cycle cycle, final T result, final Exception e) {
		this.cycle = cycle;
		if (null == lifecycle) {
			return;
		}
		try {
			switch (cycle) {
			case STARTED:
				this.lifecycle.onStart(currentThread());
				break;
			case RUNNING:
				this.lifecycle.onRunning(currentThread());
				break;
			case DONE:
				this.lifecycle.onFinish(currentThread(), result);
				break;
			case ERROR:
				this.lifecycle.onError(currentThread(), e);
				break;
			}
		} catch (final Exception ex) {
			if (cycle == Cycle.ERROR) {
				throw ex;
			}
		}
	}

	@Override
	public Cycle getCycle() {
		return this.cycle;
	}
}
