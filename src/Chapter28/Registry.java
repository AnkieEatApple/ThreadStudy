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

	// 绑定操作，subscribe这个是类的引用，同一个类new出来的不同的对象引用
	public void bind(final Object subscriber) {
		// 获取 Subscriber Object的方法集合然后进行绑定，这里将传进来的class类中的@Subscribe的方法都整理到List中
		final List<Method> subscribeMethods = getSubscribeMethods(subscriber);
		// 注册时候调用，绑定注册者引用和引用中的方法
		subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
	}

	// 解绑操作
	public void unbind(final Object subscriber) {
		// 解绑这里的操作并没有将topic对应的方法删除，而是直接将这个Subscriber封装类中的Disable置为true了
		// 在post信息的时候，一看这个diasble不是false，直接就忽略了
		subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
			if (s.getSubscribeObject() == subscriber) {
				s.setDisable(true);
			}
		}));
	}

	// 获取topic对应的队列，Register的Map中的topic对应的队列引用
	public ConcurrentLinkedQueue<Subsciber> scanSubscriber(final String topic) {
		return subscriberContainer.get(topic);
	}

	// 绑定subscriber
	private void tierSubscriber(final Object subscriber, final Method method) {
		// 引用Subscribe注解，这个反射类中方法里注释类中的注解String的getDeclaredAnnotation和getAnnotation一样
		final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
		final String topic = subscribe.topic();
		// 这块就是将反射类中的方法的注释的语句，将语句提取出来
//		System.out.println(topic);
		// 当某个topic没有subscriber Queue的时候创建一个，这个操作就骚的很，java8的新特性，这个就不用写if了
		subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
		// 创建一个Subscriber 并且加入到Subscriber列表中，Subscriber这个对象为new出来的，并且保存的是class类和对应的方法
		// **Map里面存储的是不同类new出来的对象的引用出来对应的类的筛选出来的方法**
		// **封装的是对应的一个类new出来的对象、一个类中反射出来的方法，封装成Subscriber的对象，存在queue中**
		// **调用过的时候，通过topic获取队列，按照对象调用方法，挨个执行
		// **这里封装的订阅者的是传进来的 new方法对象的引用，因为该方法在invoke的时候需要传入这个 **对象** 和参数
		subscriberContainer.get(topic).add(new Subsciber(subscriber, method));
	}

	// 将类传进来，提取里面的所有的方法，转化为List
	private List<Method> getSubscribeMethods(final Object subscriber) {
		final List<Method> methods = new ArrayList<>();
		// 通过反射机制获取该subscriber中的类temp，temp就是register中的类全名
		Class<?> temp = subscriber.getClass();
//		System.out.println(temp);
		// 不断获取当前类和父类的所有@Subscribe方法
		while (temp != null) {
			// 获取类中的所有的方法
			final Method[] declaredMethods = temp.getDeclaredMethods();
			// 只有public方法 && 有一个入参 && 最重要的是被@Subscribe标记的方法才符合回调方法
			Arrays.stream(declaredMethods).filter(m -> {
				// m.getParameterCount() == 1表示被注解的方法，只是可以有一个参数，经测试，两个参数和没有参数的都不可以哦
				// m.getModifiers() == Modifier.PUBLIC 表示被修饰的方法，只能是Public的，别的不行哦
				// m.isAnnotationPresent(Subscribe.class) 表示的是注解是否为Subscribe.class定义的
				if (m.isAnnotationPresent(Subscribe.class) && m.getParameterCount() == 1
						&& m.getModifiers() == Modifier.PUBLIC) {
//					System.out.println(m.getAnnotation(Subscribe.class).topic());
					return true;
				}
				return false;
			}).forEach(methods::add);
			// 这里就比较飘了，从这个类向上获取，获取父类看是否存在@Subscribe的注解方法
			temp = temp.getSuperclass();
//			System.out.println(temp);
		}
		// 这个就是将List转化为流，然后循环打印方法的名称，测试用的，其实转不转流都可以forEach
//		methods.stream().forEach(m -> System.out.println(m.getName()));
		return methods;
	}
}
