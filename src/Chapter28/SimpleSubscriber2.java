package Chapter28;

/**
 ********************************************************************** 
 * @Title: SimpleSubscriber2.java
 * @Description: 测试类
 * @author ankie
 * @date 2018年12月19日
 * @version 1.0
 **********************************************************************
 */
public class SimpleSubscriber2 {

	@Subscribe
	public void method1(final String message) {
		System.out.println("==SimpleSubscriber2==method1==" + message);
	}

	@Subscribe(topic = "test")
	public void method2(final String message) {
		System.out.println("==SimpleSubscriber2==method2==" + message);
	}

}
