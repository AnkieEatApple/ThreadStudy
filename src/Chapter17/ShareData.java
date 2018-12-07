package Chapter17;
/**
 **********************************************************************  
 * @Title: ShareData.java 
 * @Description:  读写锁的使用
 * @author ankie 
 * @date 2018年12月7日  
 * @version 1.0  
 **********************************************************************
 */

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ShareData {

	// 定义共享数据，资源
	private final ArrayList<Character> container = new ArrayList<>();

	// 构造readWriteLock
	private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

	// 创建读锁
	private final Lock readLock = readWriteLock.readLock();

	// 创建写入锁
	private final Lock writeLock = readWriteLock.writeLock();

	// 长度？？？
	private final int length;

	public ShareData(final int length) {
		this.length = length;
		for (int i = 0; i < length; i++) {
			container.add(i, 'c');
		}
		System.out.println(container.toString());
	}

	public char[] read() throws InterruptedException {
		try {
			// 首先使用读锁进行lock
			readLock.lock();
			final char[] newBuffer = new char[length];
			for (int i = 0; i < length; i++) {
				newBuffer[i] = container.get(i);
			}
			slowly();
			return newBuffer;
		} finally {
			// 当操作之后，将锁进行释放
			readLock.unlock();
		}
	}

	public void write(final char c) throws InterruptedException {
		try {
			// 使用锁进行lock
			writeLock.lock();
			for (int i = 0; i < length; i++) {
				this.container.add(i, c);
			}
			slowly();
		} finally {
			// 当多有的操作都完成之后，对写锁进行释放
			System.out.println(container.toString());
			writeLock.unlock();
		}
	}

	public void slowly() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
