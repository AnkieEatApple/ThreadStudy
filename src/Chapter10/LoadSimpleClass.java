package Chapter10;

public class LoadSimpleClass {

	public static void main(final String[] args)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		final BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();

		final Class<?> aClass = classLoader.loadClass("Chapter10.SimpleClass");

		System.out.println(classLoader.getParent());

		aClass.newInstance();
	}
}
