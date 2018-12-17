package Chapter27;

/**
 ********************************************************************** 
 * @Title: IllegalActiveMethod.java
 * @Description: 自定义的异常，如果接口方法有返回值，则必须要求返回Future类型才可以，否则抛出异常
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
public class IllegalActiveMethod extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalActiveMethod(final String message) {
		super(message);
	}
}
