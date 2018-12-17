package Chapter27;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestMessage extends MethodMessage {

	public TestMessage(final Map<String, Object> params, final OrderService orderService) {
		super(params, orderService);
	}

	@Override
	public void execute() {

		IntStream.range(0, 5).forEach(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("test for TestMessage");
			} catch (final Exception e) {
				// TODO: handle exception
			}
		});

	}

}
