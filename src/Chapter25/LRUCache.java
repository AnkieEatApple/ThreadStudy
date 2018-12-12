package Chapter25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LRUCache<K, V> {

	// 用于记录key值的顺序
	private final LinkedList<K> keyList = new LinkedList<>();

	// 用于存放数据
	private final Map<K, V> cache = new HashMap<>();

	// cache的最大容量
	private final int capacity;

	// cacheloader 接口提供了一种加载数据的方式
	private final CacheLoader<K, V> cacheLoader;

	public LRUCache(final int capacity, final CacheLoader<K, V> cacheLoader) {
		this.capacity = capacity;
		this.cacheLoader = cacheLoader;
	}

	public void put(final K key, final V value) {
		// 当元素数量超过容量时，将最老的数据清除
		if (keyList.size() >= capacity) {
			// 最老的数据
			final K eldestKey = keyList.removeFirst();
			cache.remove(eldestKey);
		}
		// 如果数据已经存在，则将key的队列中删除
		if (keyList.contains(key))
			keyList.remove(key);
		// 将key存放到队尾
		keyList.addLast(key);
		cache.put(key, value);
	}

	public V get(final K key) {
		V value;
		// 先将key从key的list中删除
		final boolean success = keyList.remove(key);
		// 如果删除失败则表明该数据根本不存在
		if (!success) {
			// 通过cacheloader对数据进行加载
			value = cacheLoader.load(key);
			// 调用put方法cache的数据
			this.put(key, value);
		} else {
			// 如果删除成功，则从cache中返回数据，并且将key再次放到队尾
			value = cache.get(key);
			keyList.addLast(key);
		}
		return value;
	}

	@Override
	public String toString() {
		return this.keyList.toString();
	}

	public static void main(final String[] args) {
//		final LRUCache<String, Reference> cache = new LRUCache<>(5, key -> new Reference());
//		cache.get("Alex");
//		cache.get("Jack");
//		cache.get("Gavin");
//		cache.get("Dillon");
//		cache.get("Leo");
//		// 上面的数据在缓存中的新旧程度尾Leo>Dillon>Gavin>Jack>Alex
//
//		try {
//			Thread.currentThread().sleep(1000 * 1000);
//		} catch (final InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		try {
////			TimeUnit.SECONDS.sleep(1000);
////		} catch (final InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		// Alex 将会被踢出
//		cache.get("Jenny");
//		System.out.println(cache.toString());

		final LRUCache<Integer, Reference> cache = new LRUCache<>(200, key -> new Reference());
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			cache.get(i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("The " + i + " reference stored at cache.");
		}
	}
}
