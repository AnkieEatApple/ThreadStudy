package Chapter16;

/**
 ********************************************************************** 
 * @Title: FlightSecurityTest.java
 * @Description: 对FlightSecurity的简单的测试
 * @author ankie
 * @date 2018年12月5日
 * @version 1.0
 **********************************************************************
 */
public class FlightSecurityTest {

	static class Passengers extends Thread {

		// 安检对象
		private final FlightSecurity flightSecurity;

		// 身份证
		private final String idCard;

		// 登机牌
		private final String boardingPass;

		// 构造函数
		public Passengers(final FlightSecurity flightSecurity, final String idCard, final String boradingPass) {
			this.flightSecurity = flightSecurity;
			this.idCard = idCard;
			this.boardingPass = boradingPass;
		}

		@Override
		public void run() {
			while (true) {
				// 旅客不断的过安检
				flightSecurity.pass(boardingPass, idCard);
			}
		}
	}

	public static void main(final String[] args) {
		// 定义三个旅客，身份证和登机牌的首字母均相同
		final FlightSecurity flightSecurity = new FlightSecurity();
		new Passengers(flightSecurity, "A123456", "AF123456").start();
		new Passengers(flightSecurity, "B123456", "BF123456").start();
		new Passengers(flightSecurity, "C123456", "CF123456").start();
	}
}
