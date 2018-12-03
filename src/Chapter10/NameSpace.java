package Chapter10;

public class NameSpace {

	public static void main(final String[] args) throws ClassNotFoundException {

		// 获取类加载器
		final ClassLoader classLoader = NameSpace.class.getClassLoader();

		// final BrokerDelegateClassLoader classLoader2 = new
		// BrokerDelegateClassLoader("classloader1", null);

		final Class<?> aClass = classLoader.loadClass("Chapter10.Test");

		final Class<?> bClass = classLoader.loadClass("Chapter10.Test");

		System.out.println(aClass.getClassLoader());

		System.out.println(bClass.getClassLoader());

		System.out.println(aClass.hashCode());

		System.out.println(bClass.hashCode());

		System.out.println(aClass == bClass);
	}
}
