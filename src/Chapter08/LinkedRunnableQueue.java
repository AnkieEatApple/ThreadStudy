package Chapter08;

import java.util.LinkedList;

/**
 ********************************************************************** 
 * @Title: LinkedRunnableQueue.java
 * @Description: 任务队列的实现
 * @author ankie
 * @date 2018年11月26日
 * @version 1.0
 **********************************************************************
 */
public class LinkedRunnableQueue implements RunnableQueue {

	// 任务队列的最大容量，在构造时传入
	private final int limit;

	// 若任务队列中的任务已经满了，则需要执行拒绝策略
	private final DenyPolicy denyPolicy;

	// 存放任务的队列
	private final LinkedList<Runnable> runnableList = new LinkedList<>();

	private final ThreadPool threadPool;

	// 构造方法
	public LinkedRunnableQueue(final int limit, final DenyPolicy denyPolicy, final ThreadPool threadPool) {
		super();
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	@Override
	public void offer(final Runnable runnable) {
		synchronized (runnableList) {
			if (runnableList.size() >= limit) {
				// 无法容纳新的任务时执行拒绝策略
				denyPolicy.reject(runnable, threadPool);
			} else {
				// 将任务加入到队尾，并唤醒阻塞中的线程
				runnableList.add(runnable);
				runnableList.notifyAll();
			}
		}
	}

	@Override
	public Runnable take() throws InterruptedException {

		synchronized (runnableList) {
			while (runnableList.isEmpty()) {
				try {
					// 如果任务队列中没有可执行的任务，则当前的线程将会挂起，
					// 进入 runnableList关联的 monitor waitSet 中的等待唤醒（新的任务加入）
					runnableList.wait();
				} catch (final InterruptedException e) {
					// 被中断时，需要将该异常抛出
					throw e;
				}
			}
		}
		// 从任务队列头部移除一个任务
		return runnableList.removeFirst();
	}

	@Override
	public int size() {
		synchronized (runnableList) {
			// 返回当前任务队列中的任务数
			return runnableList.size();
		}
	}
}
