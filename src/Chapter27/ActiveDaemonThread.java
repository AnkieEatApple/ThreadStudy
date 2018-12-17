package Chapter27;

/**
 ********************************************************************** 
 * @Title: ActiveDaemonThread.java
 * @Description: 为一个守护线程，主要是从queue中获取Message，然后执行execute方法
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class ActiveDaemonThread extends Thread {

	// 队列的引用
	private final ActiveMessageQueue queue;

	public ActiveDaemonThread(final ActiveMessageQueue queue) {
		super("ActiveDaemonThread");
		this.queue = queue;
		// 设置为守护线程
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			// 从队列中去除methodMessage，然后执行任务
			final ActiveMessage activeMessage = this.queue.take();
			activeMessage.execute();
		}
	}

}
