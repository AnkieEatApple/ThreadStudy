package Chapter28;

/**
 ********************************************************************** 
 * @Title: SimpleSubscriber1.java
 * @Description: EventBus 测试
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public class SimpleSubscriber1 {

	@Subscribe
	public void method1(final String message) {
		System.out.println("==SimpleSubscriber1==method1==" + message);
	}

	// 参数为两个
	@Subscribe
	public void test1(final String message, final String hello) {
		System.out.println("==SimpleSubscriber1==test1==" + message + hello);
	}

	// 没有参数
	@Subscribe
	public void test2() {
		System.out.println("==SimpleSubscriber1==test2==");
	}

	@Subscribe(topic = "test")
	public void method2(final String message) {
		System.out.println("==SimpleSubscriber1==method2==" + message);
	}
}
