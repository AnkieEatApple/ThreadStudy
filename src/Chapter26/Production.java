package Chapter26;

/**
 ********************************************************************** 
 * @Title: Production.java
 * @Description: 实现了基类的抽象类，对第一个和第二个的处理
 * @author ankie
 * @date 2018年12月14日
 * @version 1.0
 **********************************************************************
 */
public class Production extends InstructionBook {

	// 产品编号
	private final int prodID;

	public Production(final int prodID) {
		this.prodID = prodID;
	}

	@Override
	protected void firstProcess() {
		System.out.println("execute the " + prodID + " first process");
	}

	@Override
	protected void secondProcess() {
		System.out.println("execute the " + prodID + " second process");
	}
}
