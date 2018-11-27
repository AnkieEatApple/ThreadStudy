package Chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 ********************************************************************** 
 * @Title: MyClassLoader.java
 * @Description: 基于磁盘的ClassLoader
 * @author ankie
 * @date 2018年11月27日
 * @version 1.0
 **********************************************************************
 */
public class MyClassLoader extends ClassLoader {

	// 定义默认的class存放路径
	private final static Path DEFAULT_CLASS_DIR = Paths.get("G:", "classloader1");

	private final Path classDir;

	// 使用默认的class路径
	public MyClassLoader() {
		super();
		this.classDir = DEFAULT_CLASS_DIR;
	}

	// 允许传入指定路径的class路径
	public MyClassLoader(final String classDir) {
		super();
		this.classDir = Paths.get(classDir);
	}

	// 指定class路径的同时，指定父类加载器
	public MyClassLoader(final String classDir, final ClassLoader parent) {
		super();
		this.classDir = Paths.get(classDir);
	}

	// 重写父类的 findClass 方法，这是至关重要的步骤
	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {

		// 读取class的二进制数据
		final byte[] classBytes = this.readClassBytes(name);

		// 如果数据为null，或者没有读到任何信息，则抛出 ClassNotFoundException 异常
		if (null == classBytes || classBytes.length == 0) {
			throw new ClassNotFoundException("Can not load the class " + name);
		}

		// 调用 defineClass 方法定义 class
		return super.findClass(name);
	}

	// 将 class 文件读入内存
	private byte[] readClassBytes(final String name) throws ClassNotFoundException {

		// 将包名分隔符转换为文件路径分隔符
		final String classPath = name.replace(".", "/");
		final Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
		if (!classFullPath.toFile().exists()) {
			throw new ClassNotFoundException("The class " + name + " not found.");
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Files.copy(classFullPath, baos);
			return baos.toByteArray();
		} catch (final IOException e) {
			throw new ClassNotFoundException("Load the class " + name + " occur error.", e);
		}
	}

	@Override
	public String toString() {
		return "My ClassLoader";
	}
}
