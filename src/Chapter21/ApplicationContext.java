package Chapter21;

/**
 ********************************************************************** 
 * @Title: ApplicationContext.java
 * @Description: 线程上下文
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class ApplicationContext {

//	// 为线程中的数据
//	private ApplicationConfiguration configuration;
//	//
//	private RuntimeInfo runtimeInfo;

	// 采用Holder的单例模式
	private static class Holder {
		private static ApplicationContext instance = new ApplicationContext();
	}

	public static ApplicationContext getContext() {
		return Holder.instance;
	}

	// 线程中的数据的set 和 get方法
//	public void setConfiguration(final ApplicationConfiguration configuration) {
//		this.configuration = configuration;
//	}
//
//	public ApplicationConfiguration getConfiguration() {
//		return this.configuration;
//	}
//
//	public void setRunntileInfo(final RuntimeInfo runtimeInfo) {
//		this.runtimeInfo = runtimeInfo;
//	}
//
//	public RuntimeInfo getRuntimeInfo() {
//		return this.runtimeInfo;
//	}
}
