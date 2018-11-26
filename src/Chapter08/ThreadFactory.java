package Chapter08;

/**
 ********************************************************************** 
 * @Title: ThreadFactory.java
 * @Description: 创建线程的工厂接口
 * @author ankie
 * @date 2018年11月25日
 * @version 1.0
 **********************************************************************
 */
@FunctionalInterface
public interface ThreadFactory {

	Thread createThread(Runnable runnable);
}
