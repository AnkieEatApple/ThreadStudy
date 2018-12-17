package Chapter27;

import java.lang.reflect.Method;

import Chapter19.Future;

/**
 ********************************************************************** 
 * @Title: ActiveMessage.java
 * @Description: 用于收集接口方法信息和具体调用方法的
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
class ActiveMessage {

	// 接口方法的参数
	private final Object[] objects;

	// 接口方法
	private final Method method;

	// 有返回值的方法，会返回ActiveFuture<?> 类型
	private final ActiveFuture<Object> future;

	// 具体的Service接口
	private final Object service;

	// 构造ActiveMessage是有Builder来完成的
	private ActiveMessage(final Builder builder) {
		this.objects = builder.objects;
		this.method = builder.method;
		this.future = builder.future;
		this.service = builder.service;
	}

	// ActiveMessage 的方法通过反射的方式调用执行的具体实现
	public void execute() {
		try {
			// 执行接口的方法
			final Object result = method.invoke(service, objects);
			if (future != null) {
				// 如果是有返回值的接口方法，则需要通过get方法获得最终的结果
				final Future<?> realFuture = (Future<?>) result;
				final Object realResult = realFuture.get();
				// 将结果交给 ActiveFuture，接口方法的线程会的到返回
				future.finish(realResult);
			}
		} catch (final Exception e) {
			// 如果发生异常，那么有返回值的方法将会显示地指定结果为null，无返回值的接口方法则会忽略该异常
			if (future != null) {
				future.finish(null);
			}
		}
	}

	// Builder主要负责对ActiveMessage的构建，是一种典型的Gof Builder设计模式
	static class Builder {

		private Object[] objects;

		private Method method;

		private ActiveFuture<Object> future;

		private Object service;

		public Builder userMethod(final Method method) {
			this.method = method;
			return this;
		}

		public Builder returnFuture(final ActiveFuture<Object> future) {
			this.future = future;
			return this;
		}

		public Builder withObjects(final Object[] objects) {
			this.objects = objects;
			return this;
		}

		public Builder forService(final Object service) {
			this.service = service;
			return this;
		}

		public ActiveMessage build() {
			return new ActiveMessage(this);
		}
	}
}
