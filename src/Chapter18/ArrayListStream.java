package Chapter18;

import java.util.Arrays;
import java.util.List;

/**
 ********************************************************************** 
 * @Title: ArrayListStream.java
 * @Description: 不可变对象 list
 * @author ankie
 * @date 2018年12月8日
 * @version 1.0
 **********************************************************************
 */
public class ArrayListStream {
	public static void main(final String[] args) {
		// 定义一个List 并且使用 Arrays 的方式进行初始化
		final List<String> list = Arrays.asList("Java", "Thread", "Concurrency", "Scala", "Clojure");

		// 获取并行的stream，然后通过map 函数对list中的数据进行加工，最后输出
		// list虽然是在并行的环境下运行的，但是在stream的每一个操作中都是一个全新的list，根本不会影响到最原始的list
		// 也就是符合 不可变对象 的基本思想
		list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
		list.forEach(System.out::println);
	}
}
