package Chapter29;

/**
 **********************************************************************
 * @Title: Message.java
 * @Description: Message的特定的Type用于和Handler做关联
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public interface Message {
	/**
	 * 获取 Messgae的类型
	 */
	Class<? extends Message> getType();
}
