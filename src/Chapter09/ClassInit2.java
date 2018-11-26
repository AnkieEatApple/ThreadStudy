package Chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassInit2 {

	static {

		try {
			System.out.println("The ClassInit static code block will be invoke.");
			TimeUnit.SECONDS.sleep(10);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {

		IntStream.range(0, 5).forEach(i -> new Thread(ClassInit2::new));
	}
}
