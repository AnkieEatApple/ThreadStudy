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

	private final EventExceptionHandler exceptionHandler;

	public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

	public static final Executor PRE_THREAD_EXECUTOR_SERVICE = PreThreadExecutorService.INSTANCE;

	private Dispatcher(final Executor executorService, final EventExceptionHandler exceptionHandler) {
		this.executorService = executorService;
		this.exceptionHandler = exceptionHandler;
	}

	/**
	 * 
	 * @param bus
	 * @param registry
	 * @param event
	 * @param topic
	 */
	public void dispatch(final Bus bus, final Registry registry, final Object event, final String topic) {
		// 根据topic获取所有的Subscriber列表，topic为Registry中的key
		final ConcurrentLinkedQueue<Subsciber> subscibers = registry.scanSubscriber(topic);
		// 当此时获取回来的队列中没有值的话的操作，然后return
		if (null == subscibers) {
			if (exceptionHandler != null) {
				exceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " not bind yet"),
						new BaseEventContext(bus.getBusName(), null, event));
			}
			return;
		}
		// 遍历所有的方法，并且通过反射的方式进行方法调用，stream.filter的用法筛选
		subscibers.stream().filter(subsciber -> !subsciber.isDisable()).filter(subsciber -> {
			final Method subscribeMethod = subsciber.getSubscribeMethod();
			final Class<?> aClass = subscribeMethod.getParameterTypes()[0];
			return (aClass.isAssignableFrom(event.getClass()));
		}).forEach(subsciber -> realInvokeSubscribe(subsciber, event, bus));
	}

	private void realInvokeSubscribe(final Subsciber subsciber, final Object event, final Bus bus) {
		final Method subscribeMethod = subsciber.getSubscribeMethod();
		final Object subscribeObject = subsciber.getSubscribeObject();
		executorService.execute(() -> {
			try {
				subscribeMethod.invoke(subscribeObject, event);
			} catch (final Exception e) {
				if (null != exceptionHandler) {
					exceptionHandler.handle(e, new BaseEventContext(bus.getBusName(), subsciber, bus));
				}
			}
		});
	}

	public void close() {
		if (executorService instanceof ExecutorService) {
			((ExecutorService) executorService).shutdown();
		}
	}

	static Dispatcher newDispatcher(final EventExceptionHandler exceptionHandler, final Executor executor) {
		return new Dispatcher(executor, exceptionHandler);
	}

	static Dispatcher seqDispatcher(final EventExceptionHandler exceptionHandler) {
		return new Dispatcher(SEQ_EXECUTOR_SERVICE, exceptionHandler);
	}

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

		private final String eventBusName;

		private final Subsciber subsciber;

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

		@Override
		public Object getSubscriber() {
			return subsciber != null ? subsciber.getSubscribeObject() : null;
		}

		@Override
		public Method getSubscribe() {
			return subsciber != null ? subsciber.getSubscribeMethod() : null;
		}

		@Override
		public Object getEvent() {
			return this.event;
		}
	}
}
