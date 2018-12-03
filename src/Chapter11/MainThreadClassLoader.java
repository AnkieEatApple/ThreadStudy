package Chapter11;

public class MainThreadClassLoader {

	public static void main(final String[] args) {

		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}
