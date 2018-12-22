package Chapter28;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 ********************************************************************** 
 * @Title: DirectoryTargetMonitor.java
 * @Description: 目标路径变化监听的实体方法逻辑
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class DirectoryTargetMonitor {

	private WatchService watchService;

	private final EventBus eventBus;

	private final Path path;

	private volatile boolean start = false;

	public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath) {
		this(eventBus, targetPath, "");
	};

	public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath, final String... methodPaths) {
		this.eventBus = eventBus;
		this.path = Paths.get(targetPath, methodPaths);
	}

	public void startMonitor() throws Exception {
		// 获取watchService的监听对象，当然是系统默认的
		this.watchService = FileSystems.getDefault().newWatchService();
		// 对这个路径注册监听，判断路径下是否有文件的创建、文件修改、文件删除操作
		this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_CREATE);
		// 打印开始监听这个路径的log
		System.out.printf("The directory [%s] is monitoring...\n", path);
		// 确定开始的flag
		this.start = true;
		// 循环判断当前路径的变化
		while (start) {
			WatchKey watchKey = null;
			try {
				// 获取 Watchkey
				watchKey = watchService.take();
				// 循环判断当前的路径下的文件的所有events列表，获取事件的kind
				watchKey.pollEvents().forEach(event -> {
					final WatchEvent.Kind<?> kind = event.kind();
					final Path path = (Path) event.context();
					final Path child = DirectoryTargetMonitor.this.path.resolve(path);
					// 发送默认的topic下的文件改变的event
					eventBus.post(new FileChangeEvent(child, kind));
				});
			} catch (final Exception e) {
				// 若出现异常直接停止循环
				this.start = false;
			} finally {
				if (watchKey != null) {
					watchKey.reset();
				}
			}
		}
	}

	public void stopMonitor() throws Exception {
		System.out.printf("The directory [%s] monitor will be stop...\n", path);
		// 中断线程
		Thread.currentThread().interrupt();
		// 终止循环
		this.start = false;
		// 关闭watchService
		this.watchService.close();
		System.out.printf("The directory [%s] monitor will be stop done.\n", path);
	}

}
