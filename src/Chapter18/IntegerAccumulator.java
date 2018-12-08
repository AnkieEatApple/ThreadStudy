package Chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 ********************************************************************** 
 * @Title: IntegerAccumulator.java 不可变累加器不允许被继承
 * @Description:
 * @author ankie
 * @date 2018年12月8日
 * @version 1.0
 **********************************************************************
 */
public final class IntegerAccumulator {

	// init被final修饰
	private final int init;

	// 构造对象 初始为0
	public IntegerAccumulator(final int init) {
		this.init = init;
	}

	// 构造新的累加器，传入IntegerAccumulator对象，init的值
	public IntegerAccumulator(final IntegerAccumulator accumulator, final int init) {
		this.init = accumulator.getValue() + init;
	}

	// 每次相加都会产生一个新的 IntegerAccumulator
	public IntegerAccumulator add(final int i) {
		return new IntegerAccumulator(this, i);
	}

	public int getValue() {
		return this.init;
	}

	public static void main(final String[] args) {
		// 用同样的方式进行测试
		final IntegerAccumulator accumulator = new IntegerAccumulator(0);
		IntStream.range(0, 3).forEach(i -> new Thread(() -> {
			// inc初始化为0
			int inc = 0;
			while (true) {
				final int oldValue = accumulator.getValue();
				final int result = accumulator.add(inc).getValue();
				System.out.println(oldValue + " + " + inc + " = " + result);
				if (inc + oldValue != result) {
					System.out.println("ERROR: " + oldValue + " + " + inc + " = " + result);
				}
				inc++;
				slowly();
			}
		}).start());
	}

	private static void slowly() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
