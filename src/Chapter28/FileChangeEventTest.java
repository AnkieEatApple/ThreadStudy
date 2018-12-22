package Chapter28;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 ********************************************************************** 
 * @Title: FileChangeEventTest.java
 * @Description: 文件改变的测试类
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class FileChangeEventTest {

	public static void main(final String[] args) throws Exception {
		final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
		final EventBus eventBus = new AsyncEventBus(executor);
		// 注册
		eventBus.register(new FileChangeListener());
		final DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "/Users/ankie/Ankie/test");
		monitor.startMonitor();
	}
}
