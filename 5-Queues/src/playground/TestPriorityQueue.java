package playground;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class TestPriorityQueue {
    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<>();
        ThreadLocalRandom.current().ints(10,0,10)
                .peek(TestPriorityQueue::print)
                .forEach(q::add);
        System.out.println();
        for (int item : q){
            print(item);
        }
        System.out.println();
        Integer item;
        while ((item = q.poll()) != null){
            print(item);
        }
        System.out.println();
    }

    private static void print(int val) {
        System.out.println(val + " ");
    }
}
