package Chapter16;

/**
 ********************************************************************** 
 * @Title: FlightSecurity.java
 * @Description: 非线程安全的安检口类
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class FlightSecurity {

	// 数量
	private int count = 0;

	// 登机牌
	private String borardingPass = "null";

	// 身份证
	private String idCard = "null";

	public synchronized void pass(final String broadingPass, final String idCard) {
		this.borardingPass = broadingPass;
		this.idCard = idCard;
		this.count++;
		check();
	}

	private void check() {

		// 简单的测试，当登机牌和身份证的首字母不相同的时则表示检查不同通过
		if (borardingPass.charAt(0) != idCard.charAt(0)) {
			throw new RuntimeException("====Exception====" + toString());
		} else {
			System.out.println(toString());
		}
	}

	@Override
	public String toString() {
		//
		return "The " + count + " passengers, boardingPass [" + borardingPass + "], idCard [" + idCard + "]";
	}
}
