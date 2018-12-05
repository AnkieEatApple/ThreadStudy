package Chapter15;

/**
 ********************************************************************** 
 * @Title: Task.java
 * @Description: 函数式接口
 * @author ankie
 * @date 2018年12月4日
 * @version 1.0
 **********************************************************************
 */
@FunctionalInterface // 这个是函数式接口，有且只能有一个 抽象方法，可以有其他的静态方法
public interface Task<T> {

	// 任务执行接口，该接口允许有返回值
	T call();
}
