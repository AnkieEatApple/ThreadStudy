package Chapter23;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: Latch.java
 * @Description: 阀门设计 基类
 * @author ankie
 * @date 2018年12月10日
 * @version 1.0
 **********************************************************************
 */
public abstract class Latch {

	// 用于控制多少个线程完成任务时才能打开阀门
	protected int limit;

	// 通过构造函数传入 limit
	public Latch(final int limit) {

		this.limit = limit;
	}

	// 该方法会使得当前的线程一直等待，直到所有的线程都完成工作，被阻塞的线程是允许被中断的
	public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException;

	// 当前任务线程完成工作之后调用该方法使得计数器 -1
	public abstract void countDown();

	// 获取当前还有多少个线程没有完成的任务
	public abstract int getUnarrived();
}
