package Chapter08;

/**
 ********************************************************************** 
 * @Title: InternalTask.java
 * @Description: Runnable的一个实现，用于线程池的内部，这个类使用RunnableQueue
 * @author ankie
 * @date 2018年11月25日
 * @version 1.0
 **********************************************************************
 */
public class InternalTask implements Runnable {

	private final RunnableQueue runnableQueue;

	private volatile boolean running = true;

	public InternalTask(final RunnableQueue runnableQueue) {
		this.runnableQueue = runnableQueue;
	}

	@Override
	public void run() {
		while (running && !Thread.currentThread().isInterrupted()) {
			// 如果当前任务为 running 并且没有中断，则其将不断的从queue中获取runnable，
			// 然后执行 run 方法。
			try {
				final Runnable task = runnableQueue.take();
				task.run();
			} catch (final InterruptedException e) {
				running = false;
				break;
			}
		}
	}

	public void stop() {
		this.running = false;
	}
}
