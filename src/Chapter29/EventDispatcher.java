package Chapter29;

import java.util.HashMap;
import java.util.Map;

/**
 ********************************************************************** 
 * @Title: EventDispatcher.java
 * @Description:
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class EventDispatcher implements DynamicRouter<Message> {

	@SuppressWarnings("rawtypes")
	private final Map<Class<? extends Message>, Channel> routerTable;

	public EventDispatcher() {
		this.routerTable = new HashMap<>();
	}

	@Override
	public void registerChannel(final Class<? extends Message> MessageType, final Channel<? extends Message> channel) {
		//
		this.routerTable.put(MessageType, channel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void dispatch(final Message message) {
		if (routerTable.containsKey(message.getType())) {
			//
			routerTable.get(message.getType()).dispatch(message);
		} else
			throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");

	}
}
