package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton3.java
 * @Description: 懒汉式 + 同步方法 单例模式，该模式只能在同一时刻被一个线程所访问，性能低下
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
public final class Singleton3 { // final 不允许被继承

	// 实例变量
	private final byte[] data = new byte[1024];

	// 单例模式
	private static Singleton3 instance = null;

	// 构造方法
	private Singleton3() {

	}

	// 向getInstance()方法中加入同步控制，每次只有一个线程能进入
	public static synchronized Singleton3 getInstance() {
		if (instance == null) {
			instance = new Singleton3();
		}
		return instance;
	}
}
