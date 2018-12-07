package Chapter17;

/**
 ********************************************************************** 
 * @Title: ReadWriteLockImpl.java //Impl是指实现了一个或多个接口的类
 * @Description: 实现了read write的锁
 * @author ankie
 * @date 2018年12月6日
 * @version 1.0
 **********************************************************************
 */
class ReadWriteLockImpl implements ReadWriteLock {

	// 定义对象锁
	private final Object MUTEX = new Object();

	// 当前有多少个线程在 write
	private int writingWriters = 0;

	// 当前有多少个线程在等待 write
	private int waitingWriters = 0;

	// 当前有多少个线程在 read
	private int readingReaders = 0;

	// read 和 write 的偏好设置
	private boolean preferWriter;

	// 默认下 preferWrite 为True
	public ReadWriteLockImpl() {
		this(true);
	}

	// 构造函数 传入写锁偏好
	public ReadWriteLockImpl(final boolean preferWriter) {
		this.preferWriter = preferWriter;
	}

	// 创建 read lock
	@Override
	public Lock readLock() {
		return new ReadLock(this);
	}

	// 创建 write lock
	@Override
	public Lock writeLock() {
		return new WriteLock(this);
	}

	// 使写线程的数量 加1
	void incrementWritingWriters() {
		this.writingWriters++;
	}

	// 使等待写线程的数量 加1
	void incrementWaitingWriters() {
		this.waitingWriters++;
	}

	// 使读线程的数量 加1
	void incrementReadingReaders() {
		this.readingReaders++;
	}

	// 使写线程的数量 减1
	void decrementWritingWriters() {
		this.writingWriters--;
	}

	// 使等待获取写线程的数量 减1
	void derementWaitingWriters() {
		this.waitingWriters--;
	}

	// 使读取线程的数量 减1
	void derementReadingReaders() {
		this.readingReaders--;
	}

	// 获取当前线程正在等待进行写操作
	@Override
	public int getWritingWriters() {
		return this.writingWriters;
	}

	// 获取当前多少个线程正在等待获取写操作
	@Override
	public int getWaitingWriters() {
		return this.waitingWriters;
	}

	// 获取当前多少个线程正在进行读操作
	@Override
	public int getReadingReaders() {
		return this.readingReaders;
	}

	// 获取对象锁
	Object getMUTEX() {
		return this.MUTEX;
	}

	// 获取当前是否有偏向锁
	boolean getPreferWriter() {
		return this.preferWriter;
	}

	// 设置写锁偏好？？？
	void changePrefer(final boolean preferWriter) {
		this.preferWriter = preferWriter;
	}
}
