package Chapter25;

/**
 ********************************************************************** 
 * @Title: Reference.java
 * @Description: 定义一个Reference的强引用
 * @author ankie
 * @date 2018年12月12日
 * @version 1.0
 **********************************************************************
 */
public class Reference {

	private final byte[] data = new byte[2 << 19];

	@Override
	protected void finalize() throws Throwable {
		System.out.println("The reference will be GC.");
	}
}
