package Chapter29;

/**
 ********************************************************************** 
 * @Title: EventDispatcherExample.java
 * @Description:
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class EventDispatcherExample {
	/*
	 * IntputEvent 中定义了两个属性X和Y，主要用于在其他的Channel中的运算
	 */
	static class InputEvent extends Event {
		private final int x;
		private final int y;

		public InputEvent(final int x, final int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

	/**
	 * 用于存放结果的Event
	 */
	static class ResultEvent extends Event {
		private final int result;

		public ResultEvent(final int result) {
			this.result = result;
		}

		public int getResult() {
			return result;
		}
	}

	/**
	 * 处理 ResultHandler 的Handler（Channel)，只是简单的将计算结果输出到控制台
	 */
	static class ResultEventHandler implements Channel<ResultEvent> {
		@Override
		public void dispatch(final ResultEvent message) {
			System.out.println("The result is: " + message.getResult());
		}
	}

	/**
	 * InputEventHandler 需要向Router发送Event，因此在构造的时候需要传入Dispatcher
	 */
	static class InputEventHandler implements Channel<InputEvent> {

		private final EventDispatcher dispatcher;

		public InputEventHandler(final EventDispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		@Override
		public void dispatch(final InputEvent message) {
			System.out.format("X: %d, Y:%d\n", message.getX(), message.getY());
			final int result = message.getX() + message.getY();
			dispatcher.dispatch(new ResultEvent(result));
		}
	}

	public static void main(final String[] args) {
		// 构造Router
		final EventDispatcher dispatcher = new EventDispatcher();
		// 将Event 和Handler(Channel)的绑定关系注册到Dispatcher
		dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
		dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());
		dispatcher.dispatch(new InputEvent(1, 2));
	}
}
