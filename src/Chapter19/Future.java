package Chapter19;

/**
 ********************************************************************** 
 * @Title: Future.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public interface Future<T> {

	// 返回计算后的结果
	T get() throws InterruptedException;

	// 判断任务是否已经被执行完成
	boolean done();
}
