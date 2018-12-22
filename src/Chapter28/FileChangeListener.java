package Chapter28;

/**
 ********************************************************************** 
 * @Title: FileChangeListener.java
 * @Description: 文件改变的监听
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class FileChangeListener {

	@Subscribe
	public void onChange(final FileChangeEvent event) {
		System.out.format("%s-%s\n", event.getPath(), event.getKind());
	}
}
