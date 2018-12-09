package Chapter22;

/**
 ********************************************************************** 
 * @Title: BalkingTest.java
 * @Description: 对文档的测试
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class BalkingTest {

	public static void main(final String[] args) {
		new DocumentEditThread("/Users/ankie/eclipse-workspace/ThreadStudy/src/Chapter22", "balking.txt").start();
	}
}
