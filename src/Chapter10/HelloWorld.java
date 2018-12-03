package Chapter10;

public class HelloWorld {

//	public static void main(final String[] args) {
//		System.out.println(this.getClass().getPackage().getName());
//	}

	static {
		System.out.println("Hello world Class is Initialized.");
	}

	public String welcome() {
		return "Hello World";
	}

}
