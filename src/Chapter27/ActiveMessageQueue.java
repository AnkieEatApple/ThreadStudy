package Chapter27;

import java.util.LinkedList;

/**
 ********************************************************************** 
 * @Title: ActiveMessageQueue.java
 * @Description: 获取消息的队列处理方法
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class ActiveMessageQueue {

	// 用于存放提交的MethodMessage消息
	private final LinkedList<ActiveMessage> messages = new LinkedList<>();

	// 构造方法
	public ActiveMessageQueue() {
		// 启动woker线程
		new ActiveDaemonThread(this).start();
	}

	public void offer(final ActiveMessage activeMessage) {
		synchronized (this) {
			messages.addLast(activeMessage);
			// 因为只有一个线程负责take数据，因此没有必要notifyAll方法
			this.notify();
		}
	}

	public ActiveMessage take() {
		synchronized (this) {
			// 当MethodMessage 队列中没有Message的时候，执行线程进入阻塞
			while (messages.isEmpty()) {
				try {
					this.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 获取其中一个MethodMessage并且从队列中移除
			return messages.removeFirst();
		}
	}
}
