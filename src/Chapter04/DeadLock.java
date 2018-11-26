package Chapter04;

public class DeadLock {

	private final Object MUTEX_READ = new Object();

	private final Object MUTEX_WRITE = new Object();

	public void read() {

		synchronized (MUTEX_READ) {
			System.out.println(Thread.currentThread().getName() + " get Read lock");
			synchronized (MUTEX_WRITE) {
				System.out.println(Thread.currentThread().getName() + " get Write lock");
			}
			System.out.println(Thread.currentThread().getName() + " release Write lock");
		}
		System.out.println(Thread.currentThread().getName() + " release Read lock");
	}

	public void write() {

		synchronized (MUTEX_WRITE) {
			System.out.println(Thread.currentThread().getName() + " get Read lock");
			synchronized (MUTEX_READ) {
				System.out.println(Thread.currentThread().getName() + " get Write lock");
			}
			System.out.println(Thread.currentThread().getName() + " release Write lock");
		}
		System.out.println(Thread.currentThread().getName() + " release Read lock");
	}

	public static void main(final String[] args) {
		final DeadLock deadLock = new DeadLock();

		new Thread(() -> {
			while (true) {
				deadLock.read();
			}
		}, "READ-THREAD").start();

		new Thread(() -> {
			while (true) {
				deadLock.write();
			}
		}, "WRITE-THREAD").start();
	}
}
