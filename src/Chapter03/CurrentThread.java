package Chapter03;

public class CurrentThread {

	public static void main(final String[] args) {

		final Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println("test1 : " + (Thread.currentThread() == this));
			}
		};
		thread.start();

		final String name = Thread.currentThread().getName();
		System.out.println("test2 : " + Thread.currentThread().getName() + ", " + ("main".equals(name)));
	}
}
