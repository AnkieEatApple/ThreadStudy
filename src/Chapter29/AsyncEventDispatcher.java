package Chapter29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncEventDispatcher implements DynamicRouter<Event> {

	private final Map<Class<? extends Event>, AsyncChannel> routerTable;

	public AsyncEventDispatcher() {
		routerTable = new ConcurrentHashMap<>();
	}

	@Override
	public void registerChannel(final Class<? extends Event> messageType, final Channel<? extends Event> channel) {
		//
		if (!(channel instanceof AsyncChannel)) {
			throw new IllegalArgumentException("The channel must be AsyncChannel Type.");
		}
		this.routerTable.put(messageType, (AsyncChannel) channel);
	}

	@Override
	public void dispatch(final Event message) {
		//
		if (routerTable.containsKey(message.getType())) {
			routerTable.get(message.getType()).dispatch(message);
		} else {
			throw new MessageMatcherException("Can't match the channel for [" + message + "] type");
		}
	}

	public void shutdown() {
		//
		routerTable.values().forEach(AsyncChannel::stop);
	}
}
