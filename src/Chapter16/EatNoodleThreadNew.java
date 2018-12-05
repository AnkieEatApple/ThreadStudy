package Chapter16;

/**
 ********************************************************************** 
 * @Title: EatNoodleThreadNew.java
 * @Description: 避免死锁的方法
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class EatNoodleThreadNew extends Thread {

	//
	private final String name;

	//
	private final TablewarePair tablewarePair;

	public EatNoodleThreadNew(final String name, final TablewarePair tablewarePair) {
		this.name = name;
		this.tablewarePair = tablewarePair;
	}

	@Override
	public void run() {
		while (true) {
			this.eat();
		}
	}

	// 这个同步的是 TablewarePair对象，相当于A线程和B线程同时抢 TablewarePair的这个对象
	// 抢到了这个对象，相当于同时抢到了 fork 和 knife 两个对象，所以不会对 fork 和 knife 造成死锁
	private void eat() {
		synchronized (tablewarePair) {
			System.out.println(name + " take up " + tablewarePair.getLeftTool() + "\t(left)");
			System.out.println(name + " take up " + tablewarePair.getRightTool() + "\t(right)");
			System.out.println(name + " is eating now.");
			System.out.println(name + " put down " + tablewarePair.getRightTool() + "\t(Right)");
			System.out.println(name + " put down " + tablewarePair.getLeftTool() + "\t(left)");
		}
	}

	public static void main(final String[] args) {

		final Tableware fork = new Tableware("fork");
		final Tableware knife = new Tableware("knife");

		final TablewarePair tablewarePair = new TablewarePair(fork, knife);

		new EatNoodleThreadNew("A", tablewarePair).start();
		new EatNoodleThreadNew("B", tablewarePair).start();
	}
}
