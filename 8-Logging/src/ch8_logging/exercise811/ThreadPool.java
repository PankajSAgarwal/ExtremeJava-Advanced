package ch8_logging.exercise811;

import java.util.concurrent.*;
import java.util.logging.*;

// Using logging ...
public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<TimedRunnable> runQueue = new LinkedBlockingQueue<>();
    public static final int MAXIMUM_POOL_SIZE = 1024;
    private static final Logger log = Logger
        .getLogger(ThreadPool.class.getName());
    private volatile boolean running = true;

    /**
     * @param poolSize the pool size which should be between 1 and
     *                 the maximum allowed
     * @throws IllegalArgumentException when the pool size is less
     *                                  than 1 or larger than the
     *                                  maximum allowed
     * @see #MAXIMUM_POOL_SIZE
     */
    public ThreadPool(int poolSize) {
        if (poolSize < 1 || poolSize > MAXIMUM_POOL_SIZE) {
            throw new IllegalArgumentException("Pool size: " + poolSize + ", "
                + "must be between 0 and " + MAXIMUM_POOL_SIZE);
        }
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
            // log that the worker thread was created (include the name)
            log.info(() -> "Worker " + worker.getName() + " created by pool");
        }
        // log that the thread pool was created (include the pool size)
        log.info(() -> "Thread pool created of size=" + poolSize);
    }

    /**
     * @param job
     * @throws IllegalArgumentException if job is null
     */
    public void submit(Runnable job) {
        if (job == null) {
            throw new IllegalArgumentException("job may not be null");
        }
        if (!running) {
            throw new IllegalStateException("pool shut down - cannot accept new jobs");
        }
        log.info(() -> "Job submitted: " + job);
        runQueue.add(new TimedRunnable(job, log.isLoggable(Level.INFO)));
    }

    private Runnable take() throws InterruptedException {
        assert runQueue != null;
        boolean logTimes = log.isLoggable(Level.INFO);
        long startTime = logTimes ? System.currentTimeMillis() : 0;
        TimedRunnable timedjob = runQueue.take();
        Runnable job = timedjob.job;
        long time = logTimes ? (System.currentTimeMillis() - startTime) : 0;
        if (logTimes) {
            log.info("runQueue.take() took " + time + "ms");
            if (timedjob.loggable) {
                long queueTime = System.currentTimeMillis() - timedjob.time;
                log.info("The job was in the queue for " + queueTime + "ms");
            }
        }

        assert job != null;
        return job;
    }

    public int getRunQueueLength() {
        assert runQueue != null;
        return runQueue.size();
    }

    public void shutdown() {
        running = false;
        log.fine("About to interrupt group");
        group.interrupt();
        log.fine("Interrupted group");
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
            log.fine(() -> "Worker " + name + " has been started");
        }

        public void run() {
            while (running && !isInterrupted()) {
                try {
                    log.info(() -> "Worker " + getName() + " is waiting for task");
                    Runnable job = take();
                    log.info(() -> "Job " + job + " about to be executed");
                    boolean logTimes = log.isLoggable(Level.INFO);
                    long startTime = logTimes ? System.currentTimeMillis() : 0;
                    job.run();
                    long time = logTimes ? System.currentTimeMillis() - startTime : 0;
                    if (logTimes) {
                        log.info("Job " + job + " completed in " + time + "ms");
                    }
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
            log.info(() -> "Worker " + getName() + " shut down");
        }
    }

    private class TimedRunnable {
        private final Runnable job;
        private long time;
        private final boolean loggable;

        public TimedRunnable(Runnable job, boolean loggable) {
            this.job = job;
            this.loggable = loggable;
            time = loggable ? System.currentTimeMillis() : 0;
        }
    }
}
