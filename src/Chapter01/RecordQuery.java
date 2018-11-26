package Chapter01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * **********************************************************************
 * 
 * @Title: RecordQuery.java
 * @Description: 查询 jdbc 策略者模式
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public class RecordQuery {

	private final Connection connection;

	public RecordQuery(final Connection connection) {
		super();
		this.connection = connection;
	}

	public <T> T query(final RowHandler<T> handler, final String sql, final Object... params) throws SQLException {

		try (PreparedStatement statement = connection.prepareStatement(sql)) {

			int index = 1;
			for (final Object param : params) {
				statement.setObject(index++, param);
			}

			final ResultSet resultSet = statement.executeQuery();

			return handler.handle(resultSet);
		}
	}

}
