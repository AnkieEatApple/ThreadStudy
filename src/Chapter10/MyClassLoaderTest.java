package Chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {

	public static void main(final String[] args) throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException, InvocationTargetException {

		// 声明一个 MyClassLoader
		final MyClassLoader classLoader = new MyClassLoader();

		// 使用 MyClassLoader
		final Class<?> aClass = classLoader.loadClass("Chapter10.HelloWorld");

		// (0) 获取classLoader
		System.out.println(aClass.getClassLoader());

		// ==== 若注释掉下面的代码，只会输出对应的类加载器 ====
		// sun.misc.Launcher$AppClassLoader@4e25154f 该类加载器为系统类加载器
		// 但是HelloWorld的静态代码没有输出，是因为使用类加载器loadClass并不会导致类的主动初始化
		// 它只是执行了加载过程中的加载阶段而已

		// (1) 注释
		final Object helloWorld = aClass.newInstance();
		System.out.println(helloWorld);

		// (2) 反射？
		final Method welcomeMethod = aClass.getMethod("welcome");

		// (3) 结果
		final String result = (String) welcomeMethod.invoke(helloWorld);
		System.out.println("Result:" + result);
	}
}
