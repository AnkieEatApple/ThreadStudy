package Chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

	private final String origin;

	private final String destination;

	private final List<String> flightList = new ArrayList<>();

	public FightQueryTask(final String airline, final String origin, final String destination) {
		super("[" + airline + "]");
		this.origin = origin;
		this.destination = destination;
	}

	@Override
	public void run() {
		super.run();
		System.out.println(getName() + "-query from " + origin + " to " + destination);
		//
		final int randomVal = ThreadLocalRandom.current().nextInt(10);
		try {
			// 睡眠了？
			TimeUnit.SECONDS.sleep(randomVal);
			// 添加到 list
			this.flightList.add(getName() + "-" + randomVal);
			System.out.println("The Fight:" + getName() + "list query successful!");

		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> get() {
		return flightList;
	}
}
