package Chapter26;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Test {

	public static void main(final String[] args) {
		// 流水线上有5个工人
		final ProductionChannel channel = new ProductionChannel(5);

		// 随机数字
		final AtomicInteger productionNo = new AtomicInteger();

		// 流水线上有8个工作人员往传送带上不断地防止等待加工的半成品
		IntStream.range(1, 8).forEach(i -> new Thread(() -> {
			while (true) {
				channel.offerProduction(new Production(productionNo.getAndIncrement()));
				try {
					TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start());
	}
}
