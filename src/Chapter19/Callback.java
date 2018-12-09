package Chapter19;

/**
 ********************************************************************** 
 * @Title: Callback.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
@FunctionalInterface
public interface Callback<T> {

	// 任务完成之后，会调用该方法，其中T为任务执行后的结果
	void call(T t);
}
