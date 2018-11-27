package Chapter10;

public class ExtClassLoader {

	public static void main(final String[] args) {

		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println();

	}
}

// 扩展类加载器  ExtClassLoader
// 1. 扩展类加载器的父类加载器是根加载器，Java编写的
// 2. 用于加载 JAVA_HOME 下的 jre/lb/ert 子目录里面的类库
// 3. 扩展类加载器是 java.lang.URLClassloader 的子类
// 4. 完整类名 sun.misc.Launcher$ExtClassLoader
// 5. 加载器所加载的类库可以通过系统属性 java.ext.dirs 获得
// 6. 自己可以打包jar包，放在扩展类加载器路径中，扩展类加载器会负责加载你需要的类