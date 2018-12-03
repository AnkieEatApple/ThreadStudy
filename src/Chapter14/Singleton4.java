package Chapter14;
/**
 **********************************************************************  
 * @Title: Singleton4.java 
 * @Description:  Double - Check 单例模式
 * @author ankie 
 * @date 2018年12月3日  
 * @version 1.0  
 **********************************************************************
 */

import java.net.Socket;
import java.sql.Connection;

public final class Singleton4 { // final 不允许被继承

	// 实例变量
	private final byte[] data = new byte[1024];

	// 单例模式
	private static Singleton4 instance = null;

	// 连接
	Connection connection;

	// 网络
	Socket socket;

	private Singleton4() {
		// this.connection; // 初始化conn
		// this.socket; // 初始化socket
	}

	public static Singleton4 getInstance() {

		// 当 instance 为 null 时，进入同步代码块，同时判断该判断避免了每次都需要进入同步代码块，可提高效率
		if (null == instance) {
			// 只有一个线程能够获得 Singleton.class 关联的 monitor
			synchronized (Singleton4.class) {
				// 判断如果 instance 为null 则创建
				if (null == instance) {
					instance = new Singleton4();
				}
			}
		}
		return instance;
	}
}
