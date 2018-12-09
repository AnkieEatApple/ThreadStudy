package Chapter22;

import java.io.IOException;
import java.util.Scanner;

/**
 ********************************************************************** 
 * @Title: DocumentEditThread.java
 * @Description: 文档编辑线程
 * @author ankie
 * @date 2018年12月9日
 * @version 1.0
 **********************************************************************
 */
public class DocumentEditThread extends Thread {
	// 文件路径
	private final String documentPath;
	// 文件名称
	private final String documentName;
	// 用户录入的字符串对象
	private final Scanner scanner = new Scanner(System.in);

	public DocumentEditThread(final String documentPath, final String documentName) {
		super("DocumentEditThread");
		this.documentPath = documentPath;
		this.documentName = documentName;
	}

	@Override
	public void run() {
		int times = 0;
		try {
			final Document document = Document.create(documentPath, documentName);
			while (true) {
				// 获取用户的键盘的输入
				final String text = scanner.next();
				if ("quit".equals(text)) {
					document.close();
					break;
				}
				// 将内容编辑到document中
				document.edit(text);
				if (times == 5) {
					// 用户在输入5次之后进行文档的保存
					document.save();
					times = 0;
				}
				times++;
			}
		} catch (final IOException e) {
			throw new RuntimeException();
		}
	}
}
