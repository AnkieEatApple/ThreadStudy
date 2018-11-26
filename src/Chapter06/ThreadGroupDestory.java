package Chapter06;

public class ThreadGroupDestory {

	public static void main(final String[] args) {

		// final ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

		// mainGroup.list();

		final ThreadGroup group = new ThreadGroup("TestGroup");

		final ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("group.isDestoryed : " + group.isDestroyed());
		mainGroup.list();

		group.destroy();

		System.out.println("group.isDestoryed : " + group.isDestroyed());
		mainGroup.list();
	}
}
