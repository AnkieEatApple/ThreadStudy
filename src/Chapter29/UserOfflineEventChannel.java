package Chapter29;

public class UserOfflineEventChannel extends AsyncChannel {

	@Override
	protected void handle(final Event message) {
		final UserfOfflineEvent event = (UserfOfflineEvent) message;
		System.out.println("The User[" + event.getUser().getName() + "] is offline.");
	}
}
