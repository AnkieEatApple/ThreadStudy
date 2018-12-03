package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton6.java
 * @Description: Holder 方式，在实例类加载的时候不加载Holder内部类，在主动调用Holder的时候才初始化
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
public final class Singleton6 { // 记住要final哦

	// 实例变量
	private final byte[] data = new byte[1024];

	// 构造方法
	private Singleton6() {

	}

	// 在静态内部类中持有 Singleton6 的实例，并且可被直接初始化
	private static class Holder {
		private static Singleton6 instance = new Singleton6();
	}

	// 调用 getInstace 方法，事实上式获得 Holder 的 instance 静态属性
	public static Singleton6 getInstance() {
		return Holder.instance;
	}
}
