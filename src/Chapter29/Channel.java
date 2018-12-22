package Chapter29;

/**
 ********************************************************************** 
 * @Title: Channel.java
 * @Description: 每一个Channel负责处理一种类型的消息
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public interface Channel<E extends Message> {
	/**
	 * dispatch 方法用于负责Message的调度
	 */
	void dispatch(E message);
}
