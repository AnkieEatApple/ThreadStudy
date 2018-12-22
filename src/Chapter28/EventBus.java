package Chapter28;

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

	// 用于维护Subscriber的注册表，一个Bus就只是维护了一个注册表
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
		this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	// 构造方法-名字
	public EventBus(final String busName) {
		this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	// 构造方法-handler
	public EventBus(final EventExceptionHandler exceptionHandler) {
		this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
	}

	// 构造方法-内部
	EventBus(final String busName, final EventExceptionHandler exceptionHandler, final Executor executor) {
		this.busName = busName;
		// 在这里确定的分配器的具体模式，一个bus只维护了一个分配器
		this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
	}

	@Override
	public void register(final Object subsciber) {
		this.registry.bind(subsciber);
	}

	@Override
	public void unregister(final Object subsciber) {
		this.registry.unbind(subsciber);

	}

	// 发送post到默认的topic中，其中event默认 default-topic
	@Override
	public void post(final Object event) {
		this.post(event, DEFAULT_TOPIC);
	}

	// 发送post到定义的topic中，这个是post的最终方法,会根据topic的注解选择，event是选择发送的事件
	@Override
	public void post(final Object event, final String topic) {
		// registry是注册表，event是发送的字符串
		this.dispatcher.dispatch(this, registry, event, topic);
	}

	//
	@Override
	public void close() {
		this.dispatcher.close();
	}

	@Override
	public String getBusName() {
		return this.busName;
	}
}
