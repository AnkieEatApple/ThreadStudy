package Chapter16;

/**
 ********************************************************************** 
 * @Title: Tableware.java
 * @Description:
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class Tableware {

	// 用餐工具
	private final String toolName;

	// 构造方法
	public Tableware(final String toolName) {
		this.toolName = toolName;
	}

	@Override
	public String toString() {

		return "Tool: " + toolName;
	}
}
