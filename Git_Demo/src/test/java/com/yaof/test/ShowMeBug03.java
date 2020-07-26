package com.yaof.test;
import java.util.HashMap;
import java.util.Map;

public class ShowMeBug03 {
    public static void main(String[] args) {
        System.out.println("请完善TestMap类，要求只实现get、put、remove、size四个方法");
        System.out.println("要求不能使用第三方包，不能使用JDK中Map实现类");
        System.out.println("请对完成的方法进行测试，在main方法中调用验证");
        
        Map<String, Object> map = new TestMap<>();
        map.put("aaa", "bbb");
        map.put("ccc", "ddd");
        System.out.println(map.get("aaa"));
    }

    private static class TestMap<K, V> extends HashMap<K, V> implements Map<K, V> {

    	@Override
    	public V get(Object key) {
    		// TODO Auto-generated method stub
    		return super.get(key);
    	}
    	
    	@Override
    	public V put(K key, V value) {
    		// TODO Auto-generated method stub
    		return super.put(key, value);
    	}
    	
    	@Override
    	public V remove(Object key) {
    		// TODO Auto-generated method stub
    		return super.remove(key);
    	}

    	@Override
    	public int size() {
    		// TODO Auto-generated method stub
    		return super.size();
    	}
    }
}
