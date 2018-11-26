package Chapter02;

/************************************************************************
 * @Title: ThreadConstruction.java
 * @Description:
 * @author ankie
 * @date 2018年11月12日
 * @version 1.0
 ***********************************************************************/
public class ThreadConstruction {

//	private final static String PREFIX = "ALEX-";

	public static void main(final String[] args) {
		// IntStream.range(0,
		// 5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);

		// (1)
		final Thread t1 = new Thread("t1");

		// (2)
		final ThreadGroup group = new ThreadGroup("TestGroup");

		// (3)
		final Thread t2 = new Thread(group, "t2");
		final ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

		System.out.println("Main thread belong group: " + mainThreadGroup.getName());

		System.out.println("t2   thread belong group: " + t2.getThreadGroup());

		System.out.println("t1 and main belong the same group: " + (mainThreadGroup == t1.getThreadGroup()));

		System.out.println("t2 thread group not belong main group: " + (mainThreadGroup == t2.getThreadGroup()));

		System.out.println("t2 therad group belong main TestGroup: " + (group == t2.getThreadGroup()));

	}

//	private static Thread createThread(final int intName) {
//		return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
//	}
}
