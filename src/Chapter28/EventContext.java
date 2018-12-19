package Chapter28;

import java.lang.reflect.Method;

/**
 ********************************************************************** 
 * @Title: EventContext.java
 * @Description: 提供消息源、消息体、以及消息是由哪一个Subscriber的哪个subscribe方法所接受的
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public interface EventContext {

	//
	String getSource();

	//
	Object getSubscriber();

	//
	Method getSubscribe();

	//
	Object getEvent();
}
