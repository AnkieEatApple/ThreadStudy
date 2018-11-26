package Chapter01;

public class TicketWindow extends Thread {

	// 柜台名称
	private final String name;

	// 做多处理50笔业务
	private static final int MAX = 50;

	private int index = 1;

	public TicketWindow(final String name) {
		this.name = name;
	}

	@Override
	public void run() {
		super.run();
		while (index <= MAX) {
			System.out.println("柜台：" + name + "当前的号码是：" + (index++));
		}
	}

	public static void main(final String[] args) {
//		final TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
//		ticketWindow1.start();
//
//		final TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
//		ticketWindow2.start();
//
//		final TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
//		ticketWindow3.start();
//
//		final TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
//		ticketWindow4.start();

	}

}
