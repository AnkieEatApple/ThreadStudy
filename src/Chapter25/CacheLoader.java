package Chapter25;

/**
 ********************************************************************** 
 * @Title: CacheLoader.java
 * @Description: CacheLoader的接口
 * @author ankie
 * @date 2018年12月12日
 * @version 1.0
 **********************************************************************
 */
@FunctionalInterface
public interface CacheLoader<K, V> {

	// 定义加载数据的方法，返回的是 V
	V load(K k);
}
