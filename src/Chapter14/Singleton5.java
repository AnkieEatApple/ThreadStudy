package Chapter14;
/**
 **********************************************************************  
 * @Title: Singleton5.java 
 * @Description:  Volatile + Double-Check 最稳妥的单例方法，避免构造方法中的类为加载完成。
 * @author ankie 
 * @date 2018年12月3日  
 * @version 1.0  
 **********************************************************************
 */

import java.net.Socket;
import java.sql.Connection;

public final class Singleton5 { // 避免继承

	// 实例变量
	private final byte[] data = new byte[1024];

	// 单例 + volatile！！！
	private volatile static Singleton5 instance = null;

	// 连接
	Connection connection;

	// 网络
	Socket socket;

	// 构造方法
	private Singleton5() {
		// this.connection;
		// this.socket;
	}

	// 单例模式
	public static Singleton5 getInstance() {

		if (null == instance) {

			synchronized (Singleton5.class) {
				if (null == instance) {
					instance = new Singleton5();
				}
			}
		}
		return instance;
	}
}
