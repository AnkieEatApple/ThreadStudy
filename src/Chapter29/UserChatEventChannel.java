package Chapter29;

public class UserChatEventChannel extends AsyncChannel {

	@Override
	protected void handle(final Event message) {
		final UserChatEvent event = (UserChatEvent) message;
		System.out.println("The User[" + event.getUser().getName() + "] say: " + event.getMessage());
	}
}
