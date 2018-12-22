package Chapter29;

public class UserOnlineEventChannel extends AsyncChannel {

	@Override
	protected void handle(final Event message) {
		final UserOnlineEvent event = (UserOnlineEvent) message;
		System.out.println("The User [" + event.getUser().getName() + "] is online");
	}

}
