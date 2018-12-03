package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton.java
 * @Description: 饿汉式 单例模式
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
// final 不允许被继承
public final class Singleton {

	// 实例变量
	private final byte[] data = new byte[1024];

	// 在定义实例对象的时候直接初始化
	private static Singleton instance = new Singleton();

	// 私有构造函数，不允许外部new
	private Singleton() {

	}

	public static Singleton getInstance() {
		return instance;
	}
}
