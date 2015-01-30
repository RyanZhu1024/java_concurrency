package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/30/15.
 */
public class Main3 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor processor1 = new FolderProcessor("log", "/var/log");
        FolderProcessor processor2 = new FolderProcessor("log", "/var/log");
        FolderProcessor processor3 = new FolderProcessor("log", "/var/log");
        pool.execute(processor2);
        pool.execute(processor3);
        pool.execute(processor1);
        do {
            System.out.printf("***************************************** *\n");
            System.out.printf("Main: Parallelism: %d\n", pool.
                    getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.
                    getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.
                    getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.
                    getStealCount());
            System.out.printf("***************************************** *\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!processor1.isDone() || !processor2.isDone() || !processor3.isDone());
        System.out.println("Processor1: "+processor1.join().size());
        System.out.println("Processor2: "+processor2.join().size());
        System.out.println("Processor3: "+processor3.join().size());
    }
}
