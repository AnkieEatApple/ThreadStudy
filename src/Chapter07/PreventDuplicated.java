package Chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PreventDuplicated {

	private final static String LOCK_PATH = "/Users/ankie/Ankie/locks";

	private final static String LOCK_FILE = ".lock";

	private final static String PREMISSIONS = "rw-------";

	public static void main(final String[] args) throws IOException {

		// (2) 检查是否存在 .lock 文件
		checkRunning();

		// (1) 注入 Hook 线程，在程序退出时，删除lock 文件
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {

			System.out.println("The program received kill SIGNAL.");
			getLocalFile().toFile().delete();
		}));

		// (3) 简单模拟当前程序正在运行
		for (;;) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Program is running.");
		}

	}

	private static void checkRunning() throws IOException {

		final Path path = getLocalFile();
		if (path.toFile().exists()) {
			throw new RuntimeException("The program already running.");
		}

		final Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(PREMISSIONS);

		Files.createFile(path, PosixFilePermissions.asFileAttribute(permissions));
	}

	private static Path getLocalFile() {
		return Paths.get(LOCK_PATH, LOCK_FILE);
	}
}
