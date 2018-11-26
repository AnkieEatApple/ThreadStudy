package Chapter09;

public class ClassInit {

	// 父类中有静态变量 value
	static class Parent {

		static int value = 10;

		static {
			value = 20;
		}
	}

	// 子类使用父类的静态变量为自己的静态变量赋值
	static class Child extends Parent {

		static int i = value;
	}

	public static void main(final String[] args) {

		System.out.println(Child.i);
	}
}
