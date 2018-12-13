package Chapter25;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceTest {

	public static void main(final String[] args) {
		final Reference reference = new Reference();
		WeakReference<Reference> rWeakReference = new WeakReference<Reference>(reference);
		rWeakReference = null;
		// 执行GC操作
		System.gc();
		try {
			TimeUnit.DAYS.sleep(1);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
