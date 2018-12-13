package Chapter25;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 ********************************************************************** 
 * @Title: SoftLRUCache.java
 * @Description: 缓存CacheLRUCache的数据
 * @author ankie
 * @date 2018年12月12日
 * @version 1.0
 **********************************************************************
 */
public class SoftLRUCache<K, V> {

	// list储存key
	private final LinkedList<K> keyList = new LinkedList<>();

	// Value采用SoftReference进行修饰
	private final Map<K, SoftReference<V>> cache = new HashMap<>();

	// 存的数量
	private final int capacity;

	// 缓存
	private final CacheLoader<K, V> cacheLoader;

	public SoftLRUCache(final int capacity, final CacheLoader<K, V> cacheLoader) {
		this.capacity = capacity;
		this.cacheLoader = cacheLoader;
	}

	public void put(final K key, final V value) {
		if (keyList.size() >= capacity) {
			// 老数据
			final K eldestKey = keyList.removeFirst();
			cache.remove(eldestKey);
		}
		if (keyList.contains(key)) {
			keyList.remove(key);
		}
		keyList.addLast(key);
		// 保存SoftReference
		cache.put(key, new SoftReference<>(value));
	}

	public V get(final K key) {
		V value;
		final boolean success = keyList.remove(key);
		if (!success) {
			value = cacheLoader.load(key);
			this.put(key, value);
		} else {
			value = cache.get(key).get();
			keyList.addLast(key);
		}
		return value;
	}

	@Override
	public String toString() {
		return keyList.toString();
	}

	public static void main(final String[] args) {
//		final SoftLRUCache<Integer, Reference> cache = new SoftLRUCache<>(1000, new CacheLoader<Integer, Reference>() {
//
//			@Override
//			public Reference load(Integer k) {
//				return new Reference();
//			}
//			
//		});

		final SoftLRUCache<Integer, Reference> cache = new SoftLRUCache<>(1000, key -> new Reference());
		System.out.println(cache);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			cache.get(i);
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("The " + i + " reference stored at cache.");
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
