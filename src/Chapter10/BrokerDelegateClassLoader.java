package Chapter10;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class BrokerDelegateClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(final String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(name);
	}

	@Override
	protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {

		// (1) 根据类的全路径名称进行加锁，确保每一个类在多线程的情况下只被加载一次
		synchronized (getClassLoadingLock(name)) {
			// (2) 到以加载类的缓存中查看是否已经被加载，如果以加载则直接返回
			Class<?> klass = findLoadedClass(name);
			// (3) 若缓存中没有被加载的类，则需要对其进行首次加载
			if (klass == null) {
				// (4) 如果类的全路径以 java 和 javax 开头，则直接委托给系统类加载器进行加载
				if (name.startsWith("java.") || name.startsWith("javax")) {
					try {
						klass = getSystemClassLoader().loadClass(name);
					} catch (final Exception e) {
						// ignore
					}
				} else {
					// (5) 若自定义加载仍旧没有完成对类的加载，则尝试委托我们自定义的类加载进行加载
					try {
						klass = this.findClass(name);
					} catch (final ClassNotFoundException e) {
						// ignore
					}
				}
				// (6) 若自定义类加载仍旧没有完成对类的加载，则委托给其父类加载器进行加载或者系统类加载器进行加载
				if (klass == null) {
					if (getParent() != null) {
						klass = getParent().loadClass(name);
					} else {
						klass = getSystemClassLoader().loadClass(name);
					}
				}
			}
			// (7) 经过若干次的尝试后，如果还是无法对类进行加载，则抛出无法找到类的异常。
			if (null == klass) {
				throw new ClassNotFoundException("The class " + name + " not found.");
			}

			if (resolve) {
				resolveClass(klass);
			}
			return klass;
		}
	}

	@Override
	protected Object getClassLoadingLock(final String className) {
		// TODO Auto-generated method stub
		return super.getClassLoadingLock(className);
	}

	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}

	@Override
	public URL getResource(final String name) {
		// TODO Auto-generated method stub
		return super.getResource(name);
	}

	@Override
	public Enumeration<URL> getResources(final String name) throws IOException {
		// TODO Auto-generated method stub
		return super.getResources(name);
	}

	@Override
	protected URL findResource(final String name) {
		// TODO Auto-generated method stub
		return super.findResource(name);
	}

	@Override
	protected Enumeration<URL> findResources(final String name) throws IOException {
		// TODO Auto-generated method stub
		return super.findResources(name);
	}

	@Override
	public InputStream getResourceAsStream(final String name) {
		// TODO Auto-generated method stub
		return super.getResourceAsStream(name);
	}

	@Override
	protected Package definePackage(final String name, final String specTitle, final String specVersion,
			final String specVendor, final String implTitle, final String implVersion, final String implVendor,
			final URL sealBase) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return super.definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor,
				sealBase);
	}

	@Override
	protected Package getPackage(final String name) {
		// TODO Auto-generated method stub
		return super.getPackage(name);
	}

	@Override
	protected Package[] getPackages() {
		// TODO Auto-generated method stub
		return super.getPackages();
	}

	@Override
	protected String findLibrary(final String libname) {
		// TODO Auto-generated method stub
		return super.findLibrary(libname);
	}

	@Override
	public void setDefaultAssertionStatus(final boolean enabled) {
		// TODO Auto-generated method stub
		super.setDefaultAssertionStatus(enabled);
	}

	@Override
	public void setPackageAssertionStatus(final String packageName, final boolean enabled) {
		// TODO Auto-generated method stub
		super.setPackageAssertionStatus(packageName, enabled);
	}

	@Override
	public void setClassAssertionStatus(final String className, final boolean enabled) {
		// TODO Auto-generated method stub
		super.setClassAssertionStatus(className, enabled);
	}

	@Override
	public void clearAssertionStatus() {
		// TODO Auto-generated method stub
		super.clearAssertionStatus();
	}

}
