package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton2.java
 * @Description: 懒汉式 单例模式
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
public final class Singleton2 { // 不允许被继承

	// 实例变量
	private final byte[] data = new byte[1024];

	// 定义实例，但是不直接初始化
	private static Singleton2 instance = null;

	// 构造方法
	private Singleton2() {

	}

	// 懒汉式单例模式，在初始化的时候才开始加载
	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}
