package Chapter17;

/**
 ********************************************************************** 
 * @Title: ReadWriteLockTest.java
 * @Description: 读写锁的测试
 * @author ankie
 * @date 2018年12月7日
 * @version 1.0
 **********************************************************************
 */
public class ReadWriteLockTest {

	// This is the example for read write lock
	private final static String text = "Thisistheexampleforreadwritelock";

	public static void main(final String[] args) {

		// 定义共享数据
		final ShareData shareData = new ShareData(50);

		// 创建2个线程进行数据的写操作
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int index = 0; index < text.length(); index++) {
					try {
						final char c = text.charAt(index);
						shareData.write(c);
						System.out.println(Thread.currentThread().getName() + " write " + c);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		// 创建10个读线程进行数据的读操作
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					try {
						System.out.println(Thread.currentThread().getName() + " read " + new String(shareData.read()));
					} catch (final InterruptedException e2) {
						e2.printStackTrace();
					}
				}
			}).start();
		}
	}
}
