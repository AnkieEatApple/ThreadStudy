package Chapter28;

import java.lang.reflect.Method;

/**
 **********************************************************************
 * 
 * @Title: Subsciber.java
 * @Description: Subsciber封装类
 * @author ankie
 * @date 2018年12月18日
 * @version 1.0
 **********************************************************************
 */
public class Subsciber {

	// 反射的类的对象
	private final Object subscribeObject;

	// 反射对象的方法
	private final Method subscribeMethod;

	//
	private boolean disable = false;

	public Subsciber(final Object subscribeObject, final Method subscribeMethod) {
		this.subscribeObject = subscribeObject;
		this.subscribeMethod = subscribeMethod;
	}

	public Object getSubscribeObject() {
		return subscribeObject;
	}

	public Method getSubscribeMethod() {
		return subscribeMethod;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(final boolean disable) {
		this.disable = disable;
	}
}
