package Chapter01;

import java.sql.ResultSet;

/**
 * **********************************************************************
 * 
 * @Title: RowHandler.java
 * @Description: 为RecordQuery提供的接口，内部可以实现根据不同的修饰方式
 * @author ankie
 * @date 2018年11月11日
 * @version 1.0
 **********************************************************************
 */
public interface RowHandler<T> {

	T handle(ResultSet rs);
}
