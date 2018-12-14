package Chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {

	// 传送带对象
	private final ProductionChannel channel;

	// 主要用于获取一个随机值，模拟加工一个产品需要耗费一定的时间，当然每个工人操作时所花费的时间也不一样
	private final static Random random = new Random(System.currentTimeMillis());

	// 构造方法，workerName为线程名
	public Worker(final String workerName, final ProductionChannel channel) {
		super(workerName);
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 从传送带上获取产品
				final Production production = channel.takeProduction();
				System.out.println(getName() + " process the " + production);
				// 对产品进行加工
				production.create();
				TimeUnit.SECONDS.sleep(random.nextInt(10));
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
