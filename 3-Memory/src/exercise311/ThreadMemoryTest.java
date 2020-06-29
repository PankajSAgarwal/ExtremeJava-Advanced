package exercise311;


import util.SuperSimpleGCParser;

public class ThreadMemoryTest {
    public static void main(String... args) throws InterruptedException {
        SuperSimpleGCParser.showGCLogSummaryAtExit();
        ThreadPool pool = new ThreadPool(10);
        while (true) {
            pool.submit(new Runnable() {
                byte[] data = new byte[20 * 1024];

                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread());
                        System.out.println(pool.getRunQueueLength());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            Thread.sleep(10);
        }
    }
}
