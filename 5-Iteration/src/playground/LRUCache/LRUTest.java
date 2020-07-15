package playground.LRUCache;

public class LRUTest {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(5, 5);
        for (int i = 0; i < 5; i++) {
            cache.put(i,"hi");
        }
        System.out.println(cache);//{0=hi, 1=hi, 2=hi, 3=hi, 4=hi}
        System.out.println(cache.get(2));// hi
        System.out.println(cache);//{0=hi, 1=hi, 3=hi, 4=hi, 2=hi}
        for (int i = 10; i < 14; i++) {
            cache.put(i,"hi");
        }
        System.out.println(cache);//{2=hi, 10=hi, 11=hi, 12=hi, 13=hi}
    }
}
