package Chapter29;

/**
 ********************************************************************** 
 * @Title: Event.java
 * @Description: 需要封装的Event类
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class Event implements Message {
	@Override
	public Class<? extends Message> getType() {
		return getClass();
	}
}
