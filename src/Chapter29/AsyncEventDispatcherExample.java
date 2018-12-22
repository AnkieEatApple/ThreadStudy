package Chapter29;

import java.util.concurrent.TimeUnit;

public class AsyncEventDispatcherExample {

	/**
	 * 主要用于处理InputEvent，但是需要继承AsyncChannel
	 */
	static class AsyncInputEventHandler extends AsyncChannel {
		//
		private final AsyncEventDispatcher dispatcher;

		public AsyncInputEventHandler(final AsyncEventDispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		@Override
		protected void handle(final Event message) {
			final EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;
			System.out.format("X: %d, Y: %d\n", inputEvent.getX(), inputEvent.getY());
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			final int result = inputEvent.getX() + inputEvent.getY();
			dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));
		}
	}

	/**
	 * 主要用于处理IntputEvent，但是要继承AsyncChannel
	 */
	static class AsyncResultEventHandler extends AsyncChannel {
		@Override
		protected void handle(final Event message) {
			final EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("The result is: " + resultEvent.getResult());
		}
	}

	public static void main(final String[] args) {
		//
		final AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

		//
		dispatcher.registerChannel(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
		dispatcher.registerChannel(EventDispatcherExample.ResultEvent.class, new AsyncResultEventHandler());
		//
		dispatcher.dispatch(new EventDispatcherExample.InputEvent(4, 9));
	}

}
