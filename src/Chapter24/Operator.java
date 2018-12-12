package Chapter24;

import Chapter08.BasicThreadPool;
import Chapter08.ThreadPool;

/**
 ********************************************************************** 
 * @Title: Operator.java
 * @Description: 模拟接线员处理请求
 * @author ankie
 * @date 2018年12月11日
 * @version 1.0
 **********************************************************************
 */
public class Operator {

//	public void call(final String business) {
//		// 为每一个请求创建一个线程去处理
//		final TaskHandler taskHandler = new TaskHandler(new Request(business));
//		new Thread(taskHandler).start();
//	}

	private final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

	public void call(final String business) {
		final TaskHandler taskHandler = new TaskHandler(new Request(business));
		threadPool.execute(taskHandler);
	}
}
