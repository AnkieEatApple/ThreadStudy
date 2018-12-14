package Chapter26;

/**
 ********************************************************************** 
 * @Title: ProductionChannel.java
 * @Description: 产品的传送带，在传送带上除了负责产品加工的工人之外，还有传送带上等待加工的产品
 * @author ankie
 * @date 2018年12月14日
 * @version 1.0
 **********************************************************************
 */
public class ProductionChannel {

	// 传送带上最多可以有多少个待加工的产品
	private final static int MAX_PROD = 100;

	// 主要用来存放待加工的产品，也就是传送带
	private final Production[] productionQueue; // 这个地方应该改为Queue或者LinkedList更好一点

	// 队列尾
	private int tail;

	// 队列头
	private int head;

	// 当前在流水线上有多少个待加工的产品
	private int total;

	// 在流水线上工作的工人
	private final Worker[] workers;

	// 构造方法
	public ProductionChannel(final int workerSize) {
		this.workers = new Worker[workerSize];
		this.productionQueue = new Production[MAX_PROD];
		// 实例话每一个工人的(Worker线程)并且启动
		for (int i = 0; i < workerSize; i++) {
			workers[i] = new Worker("Worker-" + i, this);
			workers[i].start();
		}
	}

	// 接受来自上游的半成品(待加工的产品)
	public void offerProduction(final Production production) {
		synchronized (this) {
			// 当传送带上待加工的产品的数量超过了最大值时，需要阻塞上游再次传送产品
			while (total >= productionQueue.length) {
				try {
					this.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 将产品放到传送带，并且通知工人线程工作
			productionQueue[tail] = production;
			tail = (tail + 1) % productionQueue.length;
			total++;
			this.notifyAll();
		}
	}

	// 工人线程(Worker)从 传送带上获取产品，并且进行加工
	public Production takeProduction() {
		synchronized (this) {
			// 当传送带上没有产品的时候，工人等待着从产品上游输出到传送带上
			while (total <= 0) {
				try {
					this.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 获取产品
			final Production production = productionQueue[head];
			head = (head + 1) % productionQueue.length;
			total--;
			this.notifyAll();
			return production;
		}
	}

}
