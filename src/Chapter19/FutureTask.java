package Chapter19;

/**
 ********************************************************************** 
 * @Title: FutureTask.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class FutureTask<T> implements Future<T> {

	// 设计结果
	private T result;
	// 任务是否完成
	private boolean isDone = false;
	// 定义对象锁
	private final Object LOCK = new Object();

	@Override
	public T get() throws InterruptedException {
		synchronized (LOCK) {
			// 当任务还没完成时，调用 get 方法会被挂起而进入阻塞
			while (!isDone) {
				LOCK.wait();
			}
		}
		// 返回最终计算结果
		return result;
	}

	// finish 方法主要用于FutureTask 设置计算结果
	protected void finish(final T result) {
		synchronized (LOCK) {
			// balking设计模式
			if (isDone)
				return;
			// 计算完成，为result指定结果，并将isDone设为true，同时唤醒阻塞中的线程
			this.result = result;
			this.isDone = true;
			LOCK.notifyAll();
		}
	}

	// 返回房前任务是否已经完成
	@Override
	public boolean done() {
		return isDone;
	}
}
