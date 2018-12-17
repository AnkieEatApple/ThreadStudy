package Chapter27;

import Chapter19.FutureTask;

/**
 ********************************************************************** 
 * @Title: ActiveFuture.java
 * @Description: 使执行线程完成任务之后传递最终结果
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class ActiveFuture<T> extends FutureTask<T> {

	// 将futureTask中的finish方法修改为public类型
	@Override
	public void finish(final T result) {
		super.finish(result);
	}
}
