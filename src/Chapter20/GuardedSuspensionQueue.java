package Chapter20;

import java.util.LinkedList;

/**
 ********************************************************************** 
 * @Title: GuardedSuspensionQueue.java 确保挂起队列的实现
 * @Description: 该模式关注点使临界值的条件是否满足，当达到设计的临界值时的相关线程则会被挂起
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class GuardedSuspensionQueue {

	// 定义存放 Integer类型的queue
	private final LinkedList<Integer> queue = new LinkedList<>();

	// 定义queue的最大容量为100
	private final int LIMIT = 100;

	// 往 queue 插入数据，如果queue 中的元素超过了最大容量，则会陷入阻塞
	public void offer(final Integer data) throws InterruptedException {

		synchronized (this) {
			// 判断 queue 的当前元素是否超过了 LIMIT
			while (queue.size() >= LIMIT) {
				// 挂起当前线程，使其陷入阻塞
				this.wait();
			}
			// 插入元素，并唤醒 take 线程
			queue.addLast(data);
			this.notifyAll();
		}
	}

	// 从队列中去除元素，如果此时队列为空，则会使当前线程阻塞
	public Integer take() throws InterruptedException {

		synchronized (this) {
			// 判断如果队列为空
			while (queue.isEmpty()) {
				// 则挂起当前的线程
				this.wait();
			}
			// 通知offer 线程可以继续插入数据了
			this.notifyAll();
			return queue.removeFirst();
		}
	}
}
