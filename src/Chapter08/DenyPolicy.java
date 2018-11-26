package Chapter08;

/**
 ********************************************************************** 
 * @Title: DenyPolicy.java
 * @Description: 队列在达到上限时，决定采用那种决策通知提交者
 * @author ankie
 * @date 2018年11月25日
 * @version 1.0
 **********************************************************************
 */
public interface DenyPolicy {

	// 拒绝方法，在不同的策略中实现
	void reject(Runnable runnable, ThreadPool threadPool);

	// 该决绝策略会直接将任务丢弃
	class DiscardDenyPolicy implements DenyPolicy {
		@Override
		public void reject(final Runnable runnable, final ThreadPool threadPool) {
			// do nothing
		}
	}

	// 该拒绝策略会向任务提交者抛出异常
	class AbortDenyPolicy implements DenyPolicy {
		@Override
		public void reject(final Runnable runnable, final ThreadPool threadPool) {

			throw new RunnableDenyException("The runnable " + runnable + " will be abort.");
		}
	}

	// 该拒绝策略会使任务在提交者所在的线程中执行任务
	class RunnerDenyPolicy implements DenyPolicy {
		@Override
		public void reject(final Runnable runnable, final ThreadPool threadPool) {
			if (!threadPool.isShutdown()) {
				runnable.run();
			}
		}
	}
}
