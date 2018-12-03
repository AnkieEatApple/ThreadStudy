package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton7.java
 * @Description: 枚举 enum方式
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
public enum Singleton7 {

	//
	INSTANCE;

	// 实例变量
	private final byte[] data = new byte[1024];

	// 构造方法
	private Singleton7() {

	}

	// 调用该方法则会主动使用 Singleton，INSTANCE将会被实例化
	private static void method() {

	}

	public static Singleton7 getInstance() {
		return INSTANCE;
	}
}
