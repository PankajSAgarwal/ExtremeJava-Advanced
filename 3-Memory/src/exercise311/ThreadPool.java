package exercise311;

import java.util.concurrent.*;

public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Runnable> runQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
        }
    }

    public boolean submit(Runnable job) {
        return runQueue.offer(job);
    }

    private Runnable take() throws InterruptedException {
        return runQueue.take();
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
            super(group, name);
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
        }
    }
}
