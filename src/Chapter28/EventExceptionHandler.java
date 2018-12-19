package Chapter28;

/**
 ********************************************************************** 
 * @Title: EventExceptionHandler.java
 * @Description: 注册一个异常回调接口，就可以知道进行消息广播推送的时候都发生了什么
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public interface EventExceptionHandler {

	//
	void handle(Throwable cause, EventContext context);
}
