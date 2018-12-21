package Chapter28;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 ********************************************************************** 
 * @Title: Dispatcher.java
 * @Description: 将EventBus post的event推送给每一个注册到topic上的Subscriber上
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public class Dispatcher {
	// 线程池，这个线程池使用的是
	private final Executor executorService;
	// 这个是一个抛异常的handler
	private final EventExceptionHandler exceptionHandler;
	// 这个就不一样了，这个是一个继承Exector的服务？
	public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;
	// 和上面一样？？？
	public static final Executor PRE_THREAD_EXECUTOR_SERVICE = PreThreadExecutorService.INSTANCE;

	private Dispatcher(final Executor executorService, final EventExceptionHandler exceptionHandler) {
		this.executorService = executorService;
		this.exceptionHandler = exceptionHandler;
	}

	// dispatch 分派任务？
	public void dispatch(final Bus bus, final Registry registry, final Object event, final String topic) {
		// 根据topic获取所有的Subscriber *队列* ，topic为Registry中的key
		final ConcurrentLinkedQueue<Subsciber> subscibers = registry.scanSubscriber(topic);
		// 当此时获取回来的队列中没有值的话的操作，然后return
		if (null == subscibers) {
			if (exceptionHandler != null) {
				exceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " not bind yet"),
						new BaseEventContext(bus.getBusName(), null, event));
			}
			return;
		}
		// 遍历所有的方法，并且通过反射的方式进行方法调用，stream.filter的用法筛选，
		// 感觉这个isDisable没用上，应该是判断当前对象是否存在用的，第一道筛选
		subscibers.stream().filter(subsciber -> !subsciber.isDisable())
				// 第二道筛选，
				.filter(subsciber -> {
					final Method subscribeMethod = subsciber.getSubscribeMethod();
					// 获取存储Subscriber中的方法的第一个参数的参数的类型
					final Class<?> aClass = subscribeMethod.getParameterTypes()[0];
					// 判断获取的方法的第一个参数的数据类型和传进来的参数的数据类型是否相同，相同的话，才会返回true，然后才会forEach
					// isAssignableFrom的方法是判断两个数据的类型是否相同的方法，这里是判断是否都是String
					return (aClass.isAssignableFrom(event.getClass()));
				}).forEach(subsciber -> realInvokeSubscribe(subsciber, event, bus));
	}

	// 循环执行了这个方法，subscriber是提取出来的封装的对象，event是需要发送给订阅者的字符串，bus是上下文，上文中的EventBus线程
	private void realInvokeSubscribe(final Subsciber subsciber, final Object event, final Bus bus) {
		// 根据subscriber获取方法？
		final Method subscribeMethod = subsciber.getSubscribeMethod();
		// 根据subscriber获取对象？
		final Object subscribeObject = subsciber.getSubscribeObject();
		// 执行服务
		executorService.execute(() -> {
			try {
				// 这里是真正调用的反射中的方法，方法名调用了invoke，传入的是new的对象和参数
				// 因为是循环发送，就相当于对这个 **队列** 中的所有的封装了的订阅者发送了这个方法的信息
				subscribeMethod.invoke(subscribeObject, event);
			} catch (final Exception e) {
				if (null != exceptionHandler) {
					exceptionHandler.handle(e, new BaseEventContext(bus.getBusName(), subsciber, bus));
				}
			}
		});
	}

	// 关闭执行上面发送方法的线程，就没有办法想队列中的订阅者发送event字符串了
	public void close() {
		if (executorService instanceof ExecutorService) {
			((ExecutorService) executorService).shutdown();
		}
	}

	// 获取新的Dispatcher的工厂类
	static Dispatcher newDispatcher(final EventExceptionHandler exceptionHandler, final Executor executor) {
		return new Dispatcher(executor, exceptionHandler);
	}

	// 工厂类
	static Dispatcher seqDispatcher(final EventExceptionHandler exceptionHandler) {
		return new Dispatcher(SEQ_EXECUTOR_SERVICE, exceptionHandler);
	}

	// 工厂类
	static Dispatcher perThreadDispatcher(final EventExceptionHandler exceptionHandler) {
		return new Dispatcher(PRE_THREAD_EXECUTOR_SERVICE, exceptionHandler);
	}

	// 顺序执行的 ExecutorService
	private static class SeqExecutorService implements Executor {
		private final static SeqExecutorService INSTANCE = new SeqExecutorService();

		@Override
		public void execute(final Runnable command) {
			command.run();
		}
	}

	private static class PreThreadExecutorService implements Executor {
		private final static PreThreadExecutorService INSTANCE = new PreThreadExecutorService();

		@Override
		public void execute(final Runnable command) {
			command.run();
		}
	}

	// 每个线程负责一次消息推送
	private static class BaseEventContext implements EventContext {
		// 任务名称
		private final String eventBusName;
		// 订阅者
		private final Subsciber subsciber;
		// 事件
		private final Object event;

		public BaseEventContext(final String eventBusName, final Subsciber subsciber, final Object event) {
			this.eventBusName = eventBusName;
			this.subsciber = subsciber;
			this.event = event;
		}

		@Override
		public String getSource() {
			return this.eventBusName;
		}

		// 获取订阅者
		@Override
		public Object getSubscriber() {
			return subsciber != null ? subsciber.getSubscribeObject() : null;
		}

		// 获取订阅的方法？
		@Override
		public Method getSubscribe() {
			return subsciber != null ? subsciber.getSubscribeMethod() : null;
		}

		// 获取事件？
		@Override
		public Object getEvent() {
			return this.event;
		}
	}
}
