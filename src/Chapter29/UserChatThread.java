package Chapter29;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class UserChatThread extends Thread {

	private final User user;
	private final AsyncEventDispatcher dispatcher;

	public UserChatThread(final User user, final AsyncEventDispatcher dispatcher) {

		super(user.getName());
		this.user = user;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		try {
			//
			dispatcher.dispatch(new UserOnlineEvent(user));
			for (int i = 0; i < 5; i++) {
				//
				dispatcher.dispatch(new UserChatEvent(user, getName() + "-Hello-" + i));
				TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
			}
		} catch (final InterruptedException e) {
			e.printStackTrace();
		} finally {
			//
			dispatcher.dispatch(new UserfOfflineEvent(user));
		}
	}
}
