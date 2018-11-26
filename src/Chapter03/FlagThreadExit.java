package Chapter03;

import java.util.concurrent.TimeUnit;

public class FlagThreadExit {
	static class MyTask extends Thread {

		private volatile boolean closed = false;

		@Override
		public void run() {
			super.run();
			System.out.println("I will start work");
			while (!closed && !isInterrupted()) {
				// 正在运行
			}
			System.out.println("I will be existing");
		}

		public void close() {
			this.closed = true;
			this.interrupt();
		}
	}

	public static void main(final String[] args) throws InterruptedException {
		final MyTask t = new MyTask();
		t.start();

		TimeUnit.MINUTES.sleep(1);
		System.out.println("System will be shutdown.");
		t.close();
	}
}
