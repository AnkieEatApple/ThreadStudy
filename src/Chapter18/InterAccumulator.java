package Chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 ********************************************************************** 
 * @Title: InterAccumulator.java
 * @Description:
 * @author ankie
 * @date 2018年12月8日
 * @version 1.0
 **********************************************************************
 */
public class InterAccumulator {

	// 初始化
	private int init;

	// 构造方法
	public InterAccumulator(final int init) {
		this.init = init;
	}

	// 将init + i，并且返回
	public int add(final int i) {
		this.init += i;
		return this.init;
	}

	// 获取init的值
	public int getValue() {
		return this.init;
	}

	public static void main(final String[] args) {
		// 初始化init为0，并初始化该对象
		final InterAccumulator accumulator = new InterAccumulator(0);
		// 定义三个线程，分别启动
		IntStream.range(0, 3).forEach(i -> new Thread(() -> {
			// 定一个 inc 的值
			int inc = 0;
			while (true) {
//				// 获取原先的值
//				final int oldValue = accumulator.getValue();
//				// 获取相加之后的值
//				final int result = accumulator.add(inc);
				int oldValue;
				int result;
				// 使用class实例作为同步锁
				synchronized (InterAccumulator.class) {
					oldValue = accumulator.getValue();
					result = accumulator.add(inc);
				}
				// 打印这个值
				System.out.println(oldValue + " + " + inc + " = " + result);
				// 当相加之后的值不对的时候，报错
				if (inc + oldValue != result) {
					System.out.println("ERROR:" + oldValue + " + " + inc + " = " + result);
				}
				// inc自增1
				inc++;
				// 模拟延时
				slowly();
			}
		}).start());
	}

	public static void slowly() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
