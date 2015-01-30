package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by shuxuan on 1/27/15.
 */
public class FactorialMain {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer num = random.nextInt(10);
            FactorialCalculator factorialCalculator = new FactorialCalculator(num);
            Future<Integer> result = threadPoolExecutor.submit(factorialCalculator);
            resultList.add(result);
        }
        do {
            System.out.println("Main: currently done tasks: " + threadPoolExecutor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> future = resultList.get(i);
                System.out.println("Task: " + i + " is done? " + future.isDone());
            }
//            try {
//                TimeUnit.MILLISECONDS.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } while (threadPoolExecutor.getCompletedTaskCount() < resultList.size());// similar to the cyclicbarrier
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> future = resultList.get(i);
            try {
                System.out.println("Task: " + i + " has value: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            threadPoolExecutor.shutdown();
        }
    }
}
