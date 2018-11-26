package Chapter01;

/**
 * **********************************************************************
 * 
 * @Title: TemplateMethod.java
 * @Description: 线程理解
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public class TemplateMethod {

	private final void print(final String message) {
		System.out.println("###############");
		wrapPrint(message);
		System.out.println("###############");

	}

	protected void wrapPrint(final String message) {
		// 等待复写

	}

	public static void main(final String[] args) {
		final TemplateMethod t1 = new TemplateMethod() {
			@Override
			protected void wrapPrint(final String message) {
				// TODO Auto-generated method stub
				super.wrapPrint(message);
				System.out.println("*" + message + "*");
			}
		};
		t1.print("hello Thread");

		final TemplateMethod t2 = new TemplateMethod() {
			@Override
			protected void wrapPrint(final String message) {
				// TODO Auto-generated method stub
				super.wrapPrint(message);
				System.out.println("+" + message + "+");
			}
		};
		t2.print("hello thread");

	}
}
