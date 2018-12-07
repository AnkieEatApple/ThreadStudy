package Chapter17;

/**
 ********************************************************************** 
 * @Title: WriteLock.java
 * @Description: WriteLock 包可见
 * @author ankie
 * @date 2018年12月6日
 * @version 1.0
 **********************************************************************
 */
class WriteLock implements Lock {

	// 读写锁对象
	private final ReadWriteLockImpl readWriteLock;

	// 构造方法
	public WriteLock(final ReadWriteLockImpl readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	@Override
	public void lock() throws InterruptedException {
		// 对读写锁中同步
		synchronized (readWriteLock.getMUTEX()) {
			try {
				// 增加等待的写线程数量 +1
				readWriteLock.incrementWaitingWriters();
				// 当正在读的线程数量 > 0，或者 在等待写的线程数量 > 0，读写锁等待
				while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
					readWriteLock.getMUTEX().wait();
				}
			} finally {
				// 读写锁对象中的等待线程 -1
				this.readWriteLock.derementWaitingWriters();
			}
			// 读写锁对象中的写线程 +1
			readWriteLock.incrementWritingWriters();
		}
	}

	@Override
	public void unlock() {
		// 获取读写锁
		synchronized (readWriteLock.getMUTEX()) {
			// 读写锁对象中的 写线程 -1
			readWriteLock.decrementWritingWriters();
			// 读写锁对象中的 偏好设置为false，可以使读锁被快速获得
			readWriteLock.changePrefer(false);
			// 通知唤醒其他在 Mutex monitor wait set 中的线程
			readWriteLock.getMUTEX().notifyAll();
		}
	}
}
