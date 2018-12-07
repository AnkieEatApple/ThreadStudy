package Chapter17;

/**
 ********************************************************************** 
 * @Title: Lock.java
 * @Description:
 * @author ankie
 * @date 2018年12月6日
 * @version 1.0
 **********************************************************************
 */
public interface Lock {

	// 获取显示锁，没有获得锁的线程将被阻塞
	void lock() throws InterruptedException;

	// 释放获取的锁
	void unlock();
}
