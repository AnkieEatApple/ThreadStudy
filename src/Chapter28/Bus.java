package Chapter28;

/**
 ********************************************************************** 
 * @Title: Bus.java
 * @Description: Bus接口定义了EventBus的所有的使用方法
 * @author ankie
 * @date 2018年12月18日
 * @version 1.0
 **********************************************************************
 */
public interface Bus {

	// 将某个对象注册到Bus上，从此之后该类就成为了Subscriber了
	void register(Object subsciber);

	// 将某个对象从Bus上取消注册，取消注册十周就不会再接受来自Bus的任何消息
	void unregister(Object subsciber);

	// 提交Event的默认topic
	void post(Object event);

	// 提交到Event到指定的topic
	void post(Object Evnet, String topic);

	// 关闭该bus
	void close();

	// 返回bud的名称标识
	String getBusName();

}
