package Chapter19;

/**
 ********************************************************************** 
 * @Title: Task.java
 * @Description:
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
@FunctionalInterface
public interface Task<IN, OUT> {

	// 给定一个参数，经过计算返回结果
	OUT get(IN input);
}
