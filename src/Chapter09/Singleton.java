package Chapter09;

/**
 ********************************************************************** 
 * @Title: Singleton.java
 * @Description: 类加载顺序
 * @author ankie
 * @date 2018年11月26日
 * @version 1.0
 **********************************************************************
 */

public class Singleton {

//	// (1) 静态变量
//	private static int x = 0;

	private static int y = 2;

	// (2) 单例模式
	private static Singleton instance = new Singleton();

	// ==== 将(1)注释掉，然后将(1)放在单例方法后面 ====
	// (3) 静态变量
	private static int x = 0;

	private Singleton() {
		x++;
		y++;
	}

	public static Singleton getInstance() {
		return instance;
	}

	public static void main(final String[] args) {

		final Singleton singleton = Singleton.instance;
		System.out.println(singleton.x);
		System.out.println(singleton.y);

		// (1)在单例方法前面的时候，先初始化类，先初始化x，y，然后初始化单例方法，然后调用构造方法

		// (2)在单例方法后面的时候，先初始化类，只是初始化了y，然后调用了单例方法，最后初始化了x？？
	}
}
