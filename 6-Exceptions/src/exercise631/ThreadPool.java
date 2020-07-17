package exercise631;

import java.util.Objects;
import java.util.concurrent.*;

// Using exceptions ...
public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Runnable> runQueue = new LinkedBlockingQueue<>();
    public static final int MAXIMUM_POOL_SIZE = 1024;
    private volatile boolean running = true;

    /**
     * @param poolSize the pool size which should be between 1 and
     *                 the maximum allowed
     * @see #MAXIMUM_POOL_SIZE
     */
    public ThreadPool(int poolSize) {
        if(poolSize < 1 || poolSize> MAXIMUM_POOL_SIZE )
            throw new OutOfRangeException(poolSize,1,MAXIMUM_POOL_SIZE + 1);
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
        }
    }

    /**
     * @param job
     */
    public void submit(Runnable job) {
        Objects.requireNonNull(job, "job==null");

        boolean offer = runQueue.offer(job);// will always be true
        assert offer;
    }

    private Runnable take() throws InterruptedException {

        Runnable job = runQueue.take();// will never be null
        assert job != null;
        return job;
    }

    public int getRunQueueLength() {
        return runQueue.size();
    }

    public void shutdown() {
        running = false;
        group.interrupt();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name); // group and name cannot be null
        }

        public void run() {
            while (running && !isInterrupted()) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
        }
    }
}
