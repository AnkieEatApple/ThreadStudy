package Chapter28;

import java.rmi.registry.Registry;
import java.util.concurrent.Executor;

/**
 ********************************************************************** 
 * @Title: EventBus.java
 * @Description: 实现了Bus的所有的功能，但是该类对Event的广播推送采取的是同步的方式
 * @author ankie
 * @date 2018年12月18日
 * @version 1.0
 **********************************************************************
 */
public class EventBus implements Bus {

	// 用于维护Subscriber的注册表
	private final Registry registry = new Registry();

	// Event Bus的名字
	private final String busName;

	// 默认的Event Bus的名字
	private final static String DEFAULT_BUS_NAME = "default";

	// 默认的tpoic的名字
	private final static String DEFAULT_TOPIC = "default-topic";

	// 用于分发广播消息到各个Subscriber的类
	private final Dispatcher dispatcher;

	// 无参构造
	public EventBus() {
		this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR);
	}

	// 构造方法-名字
	public EventBus(final String busName) {
		this(busName, null, Dispatcher.SEQ_EXECUTOR);
	}

	// 构造方法-handler
	public EventBus(final EventExceptionHandler exceptionHandler) {
		this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR);
	}

	// 构造方法-内部
	EventBus(final String busName, final EventExceptionHandler exceptionHandler, final Executor executor) {
		this.busName = busName;
		this.dispatcher = dispatcher.newDispatcher(exceptionHandler, executor);
	}

	@Override
	public void register(final Object subsciber) {
		this.registry.bind(subsciber);
	}

	@Override
	public void unregister(final Object subsciber) {
		this.registry.unbind(subsciber);

	}

	@Override
	public void post(final Object event) {
		this.post(event, DEFAULT_TOPIC);
	}

	@Override
	public void post(final Object Evnet, final String topic) {
		this.post(Evnet, topic);
	}

	@Override
	public void close() {
		this.dispatcher.close();
	}

	@Override
	public String getBusName() {
		return this.busName;
	}

}
