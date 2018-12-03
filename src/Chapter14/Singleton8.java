package Chapter14;

/**
 ********************************************************************** 
 * @Title: Singleton8.java
 * @Description: 枚举方法的改造，属于方法7
 * @author ankie
 * @date 2018年12月3日
 * @version 1.0
 **********************************************************************
 */
public class Singleton8 {

	// 实例变量
	private final byte[] data = new byte[1024];

	// 构造方法
	public Singleton8() {

	}

	// 使用枚举充当holder
	private enum EnumHolder {

		INSTANCE;

		//
		private Singleton8 instance;

		//
		EnumHolder() {
			this.instance = new Singleton8();
		}

		private Singleton8 getSingleton() {
			return instance;
		}
	}

	private static Singleton8 getInstance() {
		return EnumHolder.INSTANCE.getSingleton();
	}
}
