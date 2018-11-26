package Chapter03;

import java.util.stream.IntStream;

public class ThreadYield {

	public static void main(final String[] args) {
		IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);
	}

	private static Thread create(final int index) {
		return new Thread(() -> {
			// (1)注释部分呢
//			if (index == 0)
//				Thread.yield();
			System.out.println(index);
		});
	}

}
