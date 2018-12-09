package Chapter19;

/**
 ********************************************************************** 
 * @Title: FutureService.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public interface FutureService<IN, OUT> {

	// 提交不需要返回值的任务，Future.get 方法返回的将会是 null
	Future<?> submit(Runnable runnable);

	// 提交需要返回值的任务，其中Task接口代替了 Runnable 接口
	Future<OUT> submit(Task<IN, OUT> task, IN input);

	// 执行回调接口
	Future<OUT> submit(final Task<IN, OUT> task, final IN input, Callback<OUT> callback);

	// 使用静态方法创建一个 FuntureService 的实现
	static <T, R> FutureService<T, R> newService() {
		return new FutureServiceImpl<>();
	}
}
