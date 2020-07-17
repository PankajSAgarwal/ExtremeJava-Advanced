package exercise631;

import org.junit.*;

import java.lang.reflect.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class ThreadPoolTest {
    @Test
    public void testJobs() throws InterruptedException {
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
        assertEquals(0, pool.getRunQueueLength());
        assertTrue(time < 2400);
        pool.shutdown();
    }

    @Test
    public void testConstructorArguments() {
        try {
            new ThreadPool(-1);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(Integer.MIN_VALUE);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(0);
            fail();
        } catch (IllegalArgumentException success) {
        }
        new ThreadPool(ThreadPool.MAXIMUM_POOL_SIZE);
        try {
            new ThreadPool(ThreadPool.MAXIMUM_POOL_SIZE + 1);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(Integer.MAX_VALUE);
            fail();
        } catch (IllegalArgumentException success) {
        }
    }

    @Test
    public void testSubmitNull() {
        ThreadPool pool = new ThreadPool(10);
        try {
            pool.submit(null);
            fail();
        } catch (IllegalArgumentException success) {
        } catch (NullPointerException success) {
            assertFalse("Please throw NPE in thread pool",
                    LinkedBlockingQueue.class.getName() ==
                            success.getStackTrace()[0].getClassName());
        }
    }

    @Test
    public void testAssertions() throws NoSuchFieldException, IllegalAccessException {
        assertTrue("assert should be enabled for thread pool",
                ThreadPool.class.desiredAssertionStatus());

        Field runQueueField = ThreadPool.class.getDeclaredField("runQueue");
        runQueueField.setAccessible(true);
        ThreadPool pool = new ThreadPool(1);
        pool.submit(() -> System.out.println("Happy"));

        runQueueField.set(pool, new LinkedBlockingQueue<Runnable>() {
            public boolean offer(Runnable o) {
                return false;
            }

            public boolean add(Runnable o) {
                return false;
            }

            public Runnable take() throws InterruptedException {
                return null;
            }
        });
        boolean assertFailed = false;
        try {
            pool.submit(() -> System.out.println("Sad"));
        } catch (AssertionError expected) {
            assertFailed = true;
        }
        if (!assertFailed) fail("Assertion did not fire");
    }
}
