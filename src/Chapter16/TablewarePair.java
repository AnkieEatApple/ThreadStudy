package Chapter16;

/**
 ********************************************************************** 
 * @Title: TablewarePair.java
 * @Description: 对刀叉的进一步封装
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class TablewarePair {

	// 左手餐具
	Tableware leftTool;

	// 右手餐具
	Tableware rightTool;

	public TablewarePair(final Tableware leftTool, final Tableware rightTool) {
		this.leftTool = leftTool;
		this.rightTool = rightTool;
	}

	public Tableware getLeftTool() {
		return leftTool;
	}

	public Tableware getRightTool() {
		return rightTool;
	}
}
