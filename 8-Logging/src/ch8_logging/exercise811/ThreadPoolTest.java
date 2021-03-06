package ch8_logging.exercise811;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String... args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(19);
        long time = System.currentTimeMillis();
        ThreadPool pool = new ThreadPool(10);
        for (int i = 0; i < 19; i++) {
            pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.await();
        time = System.currentTimeMillis() - time;
        pool.shutdown();
        if (pool.getRunQueueLength() != 0) {
            throw new AssertionError("Queue was not empty: "
                + pool.getRunQueueLength());
        }
        if (time > 2400) {
            throw new AssertionError("time = " + time);
        }
        System.out.println("All is well!");
    }
}
