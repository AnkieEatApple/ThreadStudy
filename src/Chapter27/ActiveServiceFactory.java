package Chapter27;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import Chapter19.Future;

/**
 ********************************************************************** 
 * @Title: ActiveServiceFactory.java
 * @Description: 负责生成Service的代理及构建ActiveMessage
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class ActiveServiceFactory {

	// 定义ActiveMessageQueue, 用于存放ActiveMessage
	private final static ActiveMessageQueue queue = new ActiveMessageQueue();

	// 获取
	public static <T> T active(final T instance) {
		// 生成Service的代理类
		final Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
				instance.getClass().getInterfaces(), new ActiveInvocationHanler<>(instance));
		return (T) proxy;
	}

	// ActiveInvocationHanler是InvocationHandler的子类，生成Proxy的时候需要用到
	private static class ActiveInvocationHanler<T> implements InvocationHandler {

		private final T instance;

		ActiveInvocationHanler(final T instance) {
			this.instance = instance;
		}

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
			// 如果接口方法被@ActiveMethod标记，则会转换为ActiveMessage
			if (method.isAnnotationPresent(ActiveMethod.class)) {
				// 检查方法是否符合规范
				this.checkMethod(method);
				// 创建builder
				final ActiveMessage.Builder builder = new ActiveMessage.Builder();
				//
				builder.userMethod(method).withObjects(args).forService(instance);
				Object result = null;
				if (this.isReturnFutureType(method)) {
					result = new ActiveFuture<>();
					builder.returnFuture((ActiveFuture<Object>) result);
				}
				// 将ActiveMessgae加入到队列中
				queue.offer(builder.build());
				return result;
			} else {
				// 如果是普通方法(没有@ActiveMethod标记)，则会正常执行
				return method.invoke(instance, args);
			}
		}

		// 检查有返回值的方法是否为Future，否则将会抛出IllegalActiveMethod异常
		private void checkMethod(final Method method) throws IllegalActiveMethod {
			// 有返回值，必须是ActiveFuture类型的返回值
			if (!isReturnVoidType(method) && !isReturnFutureType(method)) {
				throw new IllegalActiveMethod("the method [" + method.getName() + " return type must be void/Future");
			}
		}

		// 判断方法是逗为Future返回类型
		private boolean isReturnFutureType(final Method method) {
			return method.getReturnType().isAssignableFrom(Future.class);
		}

		// 判断方法是否无返回类型
		private boolean isReturnVoidType(final Method method) {
			return method.getReturnType().equals(Void.TYPE);
		}

	}
}
