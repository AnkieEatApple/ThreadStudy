package Chapter15;

/**
 ********************************************************************** 
 * @Title: Observable.java
 * @Description: 观察者 接口
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
public interface Observable {

	// 任务生命周期的枚举类型
	enum Cycle {
		STARTED, RUNNING, DONE, ERROR
	}

	// 获取当前任务的生命周期的状态，判断当前任务处于哪个执行阶段
	Cycle getCycle();

	// 定义线程的启动方法，主要作用是为了屏蔽 Thread 的其他方法
	// 主要是为了屏蔽Thread类的其他API，可通过 Observable 的start对线程启动
	void start();

	// 定义线程的中断方法，作用与start 方法一样，也是为了屏蔽 Thread 的其他方法
	// 可通过Observable 的interrupt对当前线程进行中断
	void interrupt();
}
