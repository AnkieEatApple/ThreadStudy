package Chapter08;

/**
 ********************************************************************** 
 * @Title: ThreadPool.java
 * @Description: 线程池接口
 * @author ankie
 * @date 2018年11月25日
 * @version 1.0
 **********************************************************************
 */
public interface ThreadPool {

	// 提交任务到线程池
	void execute(Runnable runnable);

	// 关闭线程池
	void shutdown();

	// 获取线程池的初始化大小
	int getInitSize();

	// 获取线程池的核心线程数量
	int getMaxSize();

	// 获取线程池中核心线程的数量
	int getCoreSize();

	// 获取线程池中用于缓存任务队列的大小
	int getQueueSize();

	// 获取线程池中活跃线程的数量
	int getActivityCount();

	// 查看线程池是否已经被shutdown
	boolean isShutdown();
}
