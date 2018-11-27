package Chapter10;

public class ApplicationClassLoader {

	public static void main(final String[] args) {

		System.out.println(System.getProperty("java.class.path"));

		System.out.println(ApplicationClassLoader.class.getClassLoader());
	}
}

// 这个是系统类加载器
// 1. 可以通过系统属性 java.class.path 获取
// 2. 加载路径一般通过 -classpath 或者 -cp 指定
