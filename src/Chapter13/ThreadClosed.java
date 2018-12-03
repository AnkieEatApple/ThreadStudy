package Chapter13;

public class ThreadClosed extends Thread {

	//
	private volatile boolean started = true;

	@Override
	public void run() {
		super.run();

		while (started) {
			// to do work
		}
	}

	public void shutdown() {
		this.started = false;
	}
}
