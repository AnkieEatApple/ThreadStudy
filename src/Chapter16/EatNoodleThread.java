package Chapter16;

/**
 ********************************************************************** 
 * @Title: EatNoodleThread.java
 * @Description: 吃面条
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class EatNoodleThread extends Thread {

	// 名称
	private final String name;

	// 左手餐具
	private final Tableware leftTool;

	// 右手餐具
	private final Tableware rightTool;

	public EatNoodleThread(final String name, final Tableware leftTool, final Tableware rightTool) {
		this.name = name;
		this.leftTool = leftTool;
		this.rightTool = rightTool;
	}

	@Override
	public void run() {
		while (true) {
			eat();
		}
	}

	// A线程获取了fork对象作为 leftTool，然后对 fork进行同步处理，此时fork 就已经被 线程A锁定了，紧接着准备获取 rightTool
	// knife
	// B线程获取了knife对象作为 rightTool，然后对 knife 进行同步处理，此时 knife 就已经被 线程 B锁定了，紧接着准备获取
	// leftTool fork
	// 然后 A 想同步 B， B 想同步 A ，谁都不给谁，就死锁住了
	private void eat() {
		synchronized (leftTool) {
			System.out.println(name + " take up " + leftTool + "(left)");
			synchronized (rightTool) {
				System.out.println(name + " take up " + rightTool + "(right)");
				System.out.println(name + " is eating now.");
				System.out.println(name + " put down " + rightTool + "(right)");
			}
			System.out.println(name + " put down " + leftTool + "(left)");
		}
	}

	public static void main(final String[] args) {

		final Tableware fork = new Tableware("fork");
		final Tableware knife = new Tableware("knife");

		new EatNoodleThread("A", fork, knife).start();
		new EatNoodleThread("B", knife, fork).start();
	}
}
