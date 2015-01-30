package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/30/15.
 */
public class Main2 {
    public static void main(String[] args) {
        Document documentMock = new Document();
        String[][] document = documentMock.generateDocument(100, 1000, "the");
        DocumentTask documentTask = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(documentTask);
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
        } while (!documentTask.isDone());
        try {
            System.out.println("Words appeared in the document for "+documentTask.get()+" times");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        pool.shutdown();
        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
