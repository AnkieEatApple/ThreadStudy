package Chapter05;

import java.util.LinkedList;

/**
 ********************************************************************** 
 * @Title: EventQueue.java
 * @Description: 任务队列
 * @author ankie
 * @date 2018年11月18日
 * @version 1.0
 **********************************************************************
 */
public class EventQueue {
	// 队列中任务的最大数量
	private final int max;

	// 任务的类，暂未实现内容
	static class Event {

	}

	// 队列
	private final LinkedList<Event> eventQueue = new LinkedList<>();

	// 默认的队列的大小
	private final static int DEFAULT_MAX_EVENT = 10;

	// 构造函数
	public EventQueue() {

		this(DEFAULT_MAX_EVENT);
	}

	// 构造函数1
	public EventQueue(final int max) {
		this.max = max;
	}

	/**
	 * 如果event的大小超出了队列的大小，让该线程的等待
	 * 
	 * @param event 任务
	 */
	public void offer(final Event event) {
		synchronized (eventQueue) {
			if (eventQueue.size() >= max) {
				try {
					console(" the queue is full.");
					eventQueue.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}

			}
			console(" the new event is submittled");
			eventQueue.addLast(event);
			eventQueue.notify();
		}
	}

	/**
	 * 获取任务，如果队列为空，让该线程等待
	 * 
	 * @return
	 */
	public Event take() {
		synchronized (eventQueue) {
			if (eventQueue.isEmpty()) {
				try {
					console(" the queue is empty.");
					eventQueue.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			final Event event = eventQueue.removeFirst();
			this.eventQueue.notify();
			console(" the event " + event + " is handled.");
			return event;
		}
	}

	/**
	 * 打印log
	 * 
	 * @param message
	 */
	private void console(final String message) {
		System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
	}
}
