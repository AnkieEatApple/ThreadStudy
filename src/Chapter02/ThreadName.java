package Chapter02;

import java.util.stream.IntStream;

public class ThreadName {

	public static void main(final String[] args) {
		IntStream.range(0, 5).boxed().map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
				.forEach(Thread::start);
	}
}
