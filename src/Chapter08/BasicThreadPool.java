package Chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 ********************************************************************** 
 * @Title: BasicThreadPool.java
 * @Description: 基础线程池实现方法实例
 * @author ankie
 * @date 2018年11月26日
 * @version 1.0
 **********************************************************************
 */
public class BasicThreadPool extends Thread implements ThreadPool {

	// 初始化线程数量
	private final int initSize;

	// 线程池最大线程数量
	private final int maxSize;

	// 线程池核心线程数量
	private final int coreSize;

	// 当前活跃的线程数量
	private int activiteCount;

	// 创建线程所需要的工厂
	private final ThreadFactory threadFactory;

	// 任务队列
	private final RunnableQueue runnableQueue;

	// 线程池是否已经被 shutdown
	private volatile boolean isShutdown = false;

	// 工作线程队列
	private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

	private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

	private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

	private final long keepAliveTime;

	private final TimeUnit timeUnit;

	// 构造时需要传入的参数：初始的线程数量、最大的线程数量、核心线程数量、任务队列的最大数量
	public BasicThreadPool(final int initSize, final int maxSize, final int coreSize, final int queueSize) {
		this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
	}

	// 构造线程池时需要传入的参数，该构造线程函数需要的参数比较多
	public BasicThreadPool(final int initSize, final int maxSize, final int coreSize, final ThreadFactory threadFactory,
			final int queueSize, final DenyPolicy denyPolicy, final long keepAliveTime, final TimeUnit timeUnit) {
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.threadFactory = threadFactory;
		this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		this.init();
	}

	@Override
	public void execute(final Runnable runnable) {
		if (this.isShutdown) {
			throw new IllegalStateException("The thread pool is destory");
		}
		// 提交任务只是简单的往任务队列中插入 Runnable
		this.runnableQueue.offer(runnable);
	}

	@Override
	public void shutdown() {
		synchronized (this) {
			if (isShutdown) {
				return;
			}
			isShutdown = true;
			threadQueue.forEach(threadTask -> {
				threadTask.internalTask.stop();
				threadTask.thread.interrupt();
			});
			this.interrupt();
		}
	}

	@Override
	public int getInitSize() {
		if (isShutdown) {
			throw new IllegalStateException("The thread pool is destory");
		}
		return this.initSize;
	}

	@Override
	public int getMaxSize() {
		if (isShutdown) {
			throw new IllegalStateException("The thread pool is destory");
		}
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if (isShutdown) {
			throw new IllegalStateException("The thread pool is destory");
		}
		return 0;
	}

	@Override
	public int getQueueSize() {
		if (isShutdown) {
			throw new IllegalStateException("The thread pool is destory");
		}
		return runnableQueue.size();
	}

	@Override
	public int getActivityCount() {
		synchronized (this) {
			return this.activiteCount;
		}
	}

	@Override
	public boolean isShutdown() {
		return this.isShutdown;
	}

	private void init() {

		start();
		for (int i = 0; i < initSize; i++) {
			newThread();
		}
	}

	private void newThread() {
		// 创建任务线程，并且启动
		final InternalTask internalTask = new InternalTask(runnableQueue);
		final Thread thread = this.threadFactory.createThread(internalTask);
		final ThreadTask threadTask = new ThreadTask(thread, internalTask);

		threadQueue.offer(threadTask);
		this.activiteCount++;
		thread.start();
	}

	private void removeThread() {
		// 从线程池中移除某个线程
		final ThreadTask threadTask = threadQueue.remove();
		threadTask.internalTask.stop();
		this.activiteCount--;
	}

	@Override
	public void run() {
		// run 方法继承自Thread，主要用于维护线程的数量，比如扩容、回收工作等
		while (!isShutdown && !isInterrupted()) {
			try {
				timeUnit.sleep(keepAliveTime);
			} catch (final InterruptedException e) {
				isShutdown = true;
				break;
			}
			synchronized (this) {
				if (isShutdown) {
					break;
				}
				// 当前的队列中有任务尚未处理，并且activeCount < coreSize 则继续扩容
				if (runnableQueue.size() > 0 && activiteCount < coreSize) {
					for (int i = initSize; i < coreSize; i++) {
						newThread();
					}
					// continue 的目的在于不想让线程的扩容直接达到 maxSize
					continue;
				}
				// 当前的队列任务中有任务尚未处理，并且activeCount < maxSize 则继续扩容
				if (runnableQueue.size() > 0 && activiteCount < maxSize) {
					for (int i = coreSize; i < maxSize; i++) {
						newThread();
					}
				}
				// 如果任务队列中没有任务，则需要回收，回收至coreSize即可
				if (runnableQueue.size() == 0 && activiteCount > coreSize) {
					for (int i = coreSize; i < activiteCount; i++) {
						removeThread();
					}
				}
			}
		}
	}

	private static class ThreadTask {

		final Thread thread;
		final InternalTask internalTask;

		public ThreadTask(final Thread thread, final InternalTask internalTask) {
			this.thread = thread;
			this.internalTask = internalTask;
		}
	}

	private static class DefaultThreadFactory implements ThreadFactory {

		private static final AtomicInteger GROUP_COUUNTER = new AtomicInteger(1);

		private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUUNTER.getAndDecrement());

		private static final AtomicInteger COUNTER = new AtomicInteger(0);

		@Override
		public Thread createThread(final Runnable runnable) {

			return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
		}
	}
}
