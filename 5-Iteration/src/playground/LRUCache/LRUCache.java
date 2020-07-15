package playground.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int maximumCapacity;

    public LRUCache(int initialCapacity, int maximumCapacity) {
        super(initialCapacity,0.75f,true);//accessOrder=true
        this.maximumCapacity = maximumCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>maximumCapacity;
    }
}
