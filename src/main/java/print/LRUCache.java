package print;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/14
 * @description LRU缓存
 */
public class LRUCache extends LinkedHashMap<Integer,Integer> {

    int capacity;

    LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    public Integer get(Integer key) {
        return super.getOrDefault(key,-1);
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size() > capacity;
    }
}
