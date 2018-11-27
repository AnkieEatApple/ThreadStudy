package Chapter10;

public class BootStrapClassLoader {

	public static void main(final String[] args) {

		System.out.println("Bootstrap:" + String.class.getClassLoader());

		System.out.println(System.getProperty("sun.boot.class.path"));
	}
}

// 这个是 根类加载器 Bootstrap
// 1. java.lang包都是由根加载器加载的，C++编写的
// 2. 可以通过 -Xbootclasspath 来指定根加载器的路径
// 3. String.class 的类加载器是根加载器，根加载器是获取不到引用的
// 4. 根加载器所在的路径可以通过 sun.boot.class.path 系统属性来获取