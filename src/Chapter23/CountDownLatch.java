package Chapter23;

import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: countDownLatch.java
 * @Description: 无限等待的CountDownLatch的实现
 * @author ankie
 * @date 2018年12月10日
 * @version 1.0
 **********************************************************************
 */
public class CountDownLatch extends Latch {

	// 构造方法，将值传入到 基类中
	public CountDownLatch(final int limit) {
		super(limit);
	}

	@Override
	public void await(final TimeUnit unit, final long time) throws InterruptedException, WaitTimeoutException {
		if (time <= 0) {
			throw new IllegalArgumentException("This time is invalid");
		}
		// 将time 转换成秒
		long remainingNaos = unit.toNanos(time);
		// 等待任务将在 endNaos 纳秒之后倒计时
		final long endNanos = System.nanoTime() + remainingNaos;
		// 当 limit > 0时，当前线程进入到阻塞状态
		synchronized (this) {
			while (limit > 0) {
				// 如果超出时则抛出WaitTimeoutException 异常
				if (TimeUnit.NANOSECONDS.toMillis(remainingNaos) <= 0) {
					throw new WaitTimeoutException("The wait time over specify time.");
				}
				// 等待remainingNaos, 在等待的过程中可能会被中断，需要重新计算remainingNaos
				this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNaos));
				remainingNaos = endNanos - System.nanoTime();
			}
		}
	}

	@Override
	public void countDown() {
		synchronized (this) {
			if (limit <= 0) {
				throw new IllegalStateException("all of task already arrived");
			}
			// 使limit -1，并通知阻塞线程
			limit--;
			this.notifyAll();
		}
	}

	@Override
	public int getUnarrived() {
		// 返回有多少线程还未完成任务
		return limit;
	}
}
