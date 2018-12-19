package Chapter28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 ********************************************************************** 
 * @Title: Registry.java
 * @Description: 注册表维护topic和subscriber之间的关系
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public class Registry {

	// 储存Subscriber集合和topic之间的关系的map
	private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subsciber>> subscriberContainer = new ConcurrentHashMap<>();

	// 绑定操作
	public void bind(final Object subscriber) {
		// 获取 Subscriber Object的方法集合然后进行绑定
		final List<Method> subscribeMethods = getSubscribeMethods(subscriber);
		// 循环遍历方法中？？？？？？？
		subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
	}

	// 解绑操作
	public void unbind(final Object subscriber) {
		subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
			if (s.getSubscribeObject() == subscriber) {
				s.setDisable(true);
			}
		}));
	}

	// 获取topic对应的队列
	public ConcurrentLinkedQueue<Subsciber> scanSubscriber(final String topic) {
		return subscriberContainer.get(topic);
	}

	// 绑定subscriber
	private void tierSubscriber(final Object subscriber, final Method method) {
		final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
		final String topic = subscribe.topic();
		// 当某个topic没有subscriber Queue的时候创建一个
		subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
		// 创建一个Subscriber 并且加入到Subscriber列表中
		subscriberContainer.get(topic).add(new Subsciber(subscriber, method));
	}

	private List<Method> getSubscribeMethods(final Object subscriber) {
		final List<Method> methods = new ArrayList<>();
		Class<?> temp = subscriber.getClass();
		// 不断获取当前类和父类的所有@Subscribe方法
		while (temp != null) {
			// 获取所有的方法
			final Method[] declaredMethods = temp.getDeclaredMethods();
			// 只有public方法 && 有一个入参 && 最重要的是被@Subscribe标记的方法才符合回调方法
			Arrays.stream(declaredMethods).filter(m -> m.isAnnotationPresent(Subscribe.class)
					&& m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC).forEach(methods::add);
			temp = temp.getSuperclass();
		}
		return methods;
	}
}
