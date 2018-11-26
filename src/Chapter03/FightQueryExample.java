package Chapter03;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 ********************************************************************** 
 * @Title: FightQueryExample.java
 * @Description:
 * @author ankie
 * @date 2018年11月16日
 * @version 1.0
 **********************************************************************
 */
public class FightQueryExample {

	// (1) 合作的各大航空公司
	private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

	public static void main(final String[] args) {
		final List<String> results = search("SH", "BJ");
		System.out.println("==========result==========");
		results.forEach(System.out::println);
	}

	/**
	 * 查询
	 * 
	 * @param original 开始地点
	 * @param dest     目的地址
	 * @return 返回列表
	 */
	private static List<String> search(final String original, final String dest) {
		// result 列表
		final List<String> result = new ArrayList<>();

		// (2) 创建查询航班信息的线程列表
		final List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, original, dest))
				.collect(toList());

		// (3) 分别启动这几个线程
		tasks.forEach(Thread::start);

		// (4) 分别调用每一个线程的 join 方法，阻塞当前线程
		tasks.forEach(t -> {
			try {
				t.join();
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});

		// (5) 在此之前，当前线程回阻塞住，获取每一个查询线程的结果，并加入到result中
		tasks.stream().map(FightQuery::get).forEach(result::addAll);

		return result;
	}

	/**
	 * 返回FightQuery实例对象
	 * 
	 * @param fight
	 * @param original
	 * @param dest
	 * @return
	 */
	private static FightQueryTask createSearchTask(final String fight, final String original, final String dest) {
		return new FightQueryTask(fight, original, dest);
	}
}
