package Chapter24;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: TaskHandler.java
 * @Description: 处理每一个请求，由于TaskHanler将被Thread执行，因此需要实现Runnable接口
 * @author ankie
 * @date 2018年12月11日
 * @version 1.0
 **********************************************************************
 */
public class TaskHandler implements Runnable {

	//
	private final Request request;

	public TaskHandler(final Request request) {
		this.request = request;
	}

	@Override
	public void run() {
		System.out.println("Begin handle " + request);
		slowly();
		System.out.println("End handle " + request);
	}

	private void slowly() {
		try {
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
