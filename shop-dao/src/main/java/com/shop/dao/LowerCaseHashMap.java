package com.shop.dao;


import java.util.HashMap;


public class LowerCaseHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -8070414666027286684L;

	private Object getKey1(Object o) {
		if (o instanceof String) {
			return ((String) o).toLowerCase();
		} else {
			return o;
		}
	}

	@SuppressWarnings("unchecked")
	private K getKey2(K o) {
		if (o instanceof String) {
			return (K) ((String) o).toLowerCase();
		} else {
			return o;
		}
	}

	public LowerCaseHashMap() {
		super();
	}

	public LowerCaseHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @see java.util.Map#containsKey(Object)
	 */
	public boolean containsKey(Object key) {
		return super.containsKey(getKey1(key));
	}

	/**
	 * @see java.util.Map#get(Object)
	 */
	public V get(Object key) {
		return super.get(getKey1(key));
	}

	/**
	 * @see java.util.Map#put(Object, Object)
	 */
	public V put(K key, V value) {
		return super.put(getKey2(key), value);
	}

	/**
	 * @see java.util.Map#remove(Object)
	 */
	public V remove(Object key) {
		return super.remove(getKey1(key));
	}
}