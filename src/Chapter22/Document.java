package Chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 ********************************************************************** 
 * @Title: Document.java
 * @Description: 代表正在编辑的文档类 模拟word的自动保存的手段
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class Document {

	// 如果文档发生改变，changed会被设置为true
	private boolean changed = false;
	// 一次需要保存的内容，可以将其理解为内容缓存
	private final List<String> content = new ArrayList<>();
	// 文件写对象
	private final FileWriter writer;

	// 自动保存文档的线程
	private static AutoSaveThread autoSaveThread;

	// 构造函数需要传入文档保存的路径和文档名称
	public Document(final String documentPath, final String documentName) throws IOException {
		this.writer = new FileWriter(new File(documentPath, documentName), true);
	}

	// 静态方法，主要用于创建文档，顺便启动保存文档的线程
	public static Document create(final String documentPath, final String documentName) throws IOException {
		final Document document = new Document(documentPath, documentName);
		autoSaveThread = new AutoSaveThread(document);
		autoSaveThread.start();
		return document;
	}

	// 文档的编辑，其实就是content队列中提交字符串
	public void edit(final String content) {
		synchronized (this) {
			this.content.add(content);
			// 文档改变，changed会变为true
			this.changed = true;
		}
	}

	// 文档关闭的时候首先中断自动保存的线程，然后关闭writer释放资源
	public void close() throws IOException {
		autoSaveThread.interrupt();
		writer.close();
	}

	// save 方法用于为外部现实进行文档保存
	public void save() throws IOException {
		synchronized (this) {
			// balking, 如果文档已经被保存了，则直接返回
			if (!changed) {
				return;
			}
			System.out.println(Thread.currentThread() + " execute the save action");
			// 将内容写入到文档中
			for (final String cacheLine : content) {
				this.writer.write(cacheLine);
				this.writer.write("\r\n");
			}
			this.writer.flush();
			// 将changded修改为false，表明此刻没有新的内容编辑
			this.changed = false;
			this.content.clear();
		}
	}
}
