package Chapter29;

/**
 ********************************************************************** 
 * @Title: MessageMatcherException.java
 * @Description: 与Message对应的Channel的异常
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class MessageMatcherException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessageMatcherException(final String message) {
		super(message);
	}
}
