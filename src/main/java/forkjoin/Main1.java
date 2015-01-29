package forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/29/15.
 */
public class Main1 {
    public static void main(String[] args) {
        ProductGenerator productGenerator = new ProductGenerator();
        List<Product> products = productGenerator.generate(10000);
        Task task = new Task(products, 0, products.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);//asynchronous call
        do {
            System.out.printf("Main: Thread Count: %d\n", pool.
                    getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.
                    getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.
                    getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        pool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.printf("Main: The process has completed normally\n");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f\n", product.
                        getName(), product.getPrice());
            }
        }
    }
}
