package Chapter29;

public class UserOnlineEvent extends Event {

	private final User user;

	public UserOnlineEvent(final User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
