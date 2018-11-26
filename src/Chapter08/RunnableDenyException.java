package Chapter08;

/**
 ********************************************************************** 
 * @Title: RunnableDenyException.java
 * @Description: 线程拒绝异常处理
 * @author ankie
 * @date 2018年11月26日
 * @version 1.0
 **********************************************************************
 */
public class RunnableDenyException extends RuntimeException {

	/**
	 * 用来表明不同版本的兼容性
	 */
	private static final long serialVersionUID = 1L;

	public RunnableDenyException(final String message) {
		super(message);
	}
}
