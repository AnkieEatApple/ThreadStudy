package Chapter03;

public class ThreadPriority {

	public static void main(final String[] args) {
//		final Thread t1 = new Thread(() -> {
//			while (true) {
//				System.out.println("t1");
//			}
//		});
//		t1.setPriority(3);
//
//		final Thread t2 = new Thread(() -> {
//			while (true) {
//				System.out.println("t2");
//			}
//		});
//		t2.setPriority(10);
//
//		t1.start();
//		t2.start();

//		// 定义一个线程组
//		final ThreadGroup group = new ThreadGroup("test");
//		// 将线程组的优先级指定为 7
//		group.setMaxPriority(7);
//		// 定义一个线程，将该线程加入到 group 中
//		final Thread thread = new Thread(group, "test-thread");
//		// 企图将线程的优先级设定为 10
//		thread.setPriority(10);
//		// 企图未遂
//		System.out.println(thread.getPriority());

//		final Thread thread = new Thread() {
//			@Override
//			public void run() {
//				super.run();
//				System.out.println(Thread.currentThread() == this);
//			}
//		};
//		thread.start();
//
//		final String name = Thread.currentThread().getName();
//		System.out.println("main".equals(name));

		final Thread t1 = new Thread();
		System.out.println("t1 priority " + t1.getPriority());

		final Thread t2 = new Thread(() -> {
			final Thread t3 = new Thread();
			System.out.println("t3 priority " + t3.getPriority());
		});

		t2.setPriority(6);
		t2.start();
		System.out.println("t2 priority " + t2.getPriority());
	}
}
