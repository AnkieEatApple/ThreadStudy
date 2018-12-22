package Chapter29;

/**
 ********************************************************************** 
 * @Title: DynamicRouter.java
 * @Description: 帮助Event找到合适的Channel并且传送给它
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public interface DynamicRouter<E extends Message> {
	/**
	 * 针对每一种Message类型注册相关的Channel，只有找到合适的Channel该Message才会被处理
	 */
	void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

	/**
	 * 为相应的channel分配Message
	 */
	void dispatch(E message);
}
