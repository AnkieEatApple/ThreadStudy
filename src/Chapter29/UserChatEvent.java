package Chapter29;

public class UserChatEvent extends UserOnlineEvent {
	// 需要有聊天信息
	private final String message;

	public UserChatEvent(final User user, final String message) {
		super(user);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
