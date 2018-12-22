package Chapter28;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;

/**
 ********************************************************************** 
 * @Title: FileChangeEvent.java
 * @Description: 文件改变的包装类，将改变的路径和变化封装成Event对象
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class FileChangeEvent {

	private final Path path;

	private final WatchEvent.Kind<?> kind;

	public FileChangeEvent(final Path path, final Kind<?> kind) {
		this.path = path;
		this.kind = kind;
	}

	public WatchEvent.Kind<?> getKind() {
		return kind;
	}

	public Path getPath() {
		return path;
	}
}