package Chapter24;

/**
 ********************************************************************** 
 * @Title: Request.java
 * @Description: 请求的模型
 * @author ankie
 * @date 2018年12月11日
 * @version 1.0
 **********************************************************************
 */
public class Request {

	private final String business;

	public Request(final String business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return business;
	}
}
