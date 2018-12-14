package Chapter26;

/**
 ********************************************************************** 
 * @Title: InstructionBook.java
 * @Description: 在流水线上需要被加工的产品，create作为一个模版方法，提供了加工产品的说明书
 * @author ankie
 * @date 2018年12月14日
 * @version 1.0
 **********************************************************************
 */
public abstract class InstructionBook {

	public final void create() {
		this.firstProcess();
		this.secondProcess();
	}

	protected abstract void firstProcess();

	protected abstract void secondProcess();
}
