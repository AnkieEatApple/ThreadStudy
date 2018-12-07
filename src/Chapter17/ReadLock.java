package Chapter17;

/**
 ********************************************************************** 
 * @Title: ReadLock.java
 * @Description: 读锁 被设计为包可见
 * @author ankie
 * @date 2018年12月6日
 * @version 1.0
 **********************************************************************
 */
class ReadLock implements Lock {

	// 读写锁实现对象
	private final ReadWriteLockImpl readWriteLock;

	// 构造方法
	ReadLock(final ReadWriteLockImpl readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	@Override
	public void lock() throws InterruptedException {

		// 同步读写锁
		synchronized (readWriteLock.getMUTEX()) {
			// 若此时有线程在进行写操作，或者有线程在等待并且偏向写锁的标识为true时，
			// 就会无法获得读锁，只能被挂起
			while (readWriteLock.getWritingWriters() > 0
					|| readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0) {
				readWriteLock.getMUTEX().wait();
			}
			readWriteLock.incrementReadingReaders();
		}
	}

	@Override
	public void unlock() {

		// 同步读写锁
		synchronized (readWriteLock.getMUTEX()) {

			// 释放锁的过程就是使当前的reading 的数量减1
			// 将preferWriter 设置为true，可以使得 writer 线程获得更多的机会
			// 通知唤醒与 Mutex 关联 monitor waitset 中的线程
			readWriteLock.derementReadingReaders();
			readWriteLock.changePrefer(true);
			readWriteLock.getMUTEX().notifyAll();
		}
	}
}
