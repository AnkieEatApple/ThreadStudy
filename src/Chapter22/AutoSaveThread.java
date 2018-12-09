package Chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: AutoSaveThread.java
 * @Description: 自动保存的线程
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class AutoSaveThread extends Thread {

	// Document 对象
	private final Document document;

	// 构造方法
	public AutoSaveThread(final Document document) {
		super("DocumentAutoSaveThread");
		this.document = document;
	}

	@Override
	public void run() {
		// 循环体
		while (true) {
			try {
				// 每隔一秒自动保存一次文档
				document.save();
				TimeUnit.SECONDS.sleep(1);
			} catch (IOException | InterruptedException e) {
				break;
			}
		}
	}

}
