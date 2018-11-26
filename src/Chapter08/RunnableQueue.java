package Chapter08;

/**
 ********************************************************************** 
 * @Title: RunnableQueue.java
 * @Description: 任务队列接口
 * @author ankie
 * @date 2018年11月25日
 * @version 1.0
 **********************************************************************
 */
public interface RunnableQueue {

	// 当有新的任务进来时，首先会offer到队列中
	void offer(Runnable runnable);

	// 工作线程会通过take 方法获取Runnable
	Runnable take() throws InterruptedException;

	// 获取任务队列中的任务的数量
	int size();
}
