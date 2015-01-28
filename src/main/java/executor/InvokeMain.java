package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by shuxuan on 1/28/15.
 */
public class InvokeMain {
    public static void main(String[] args) {
//        ScheduledThreadPoolExecutor executorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1000);
        ScheduledThreadPoolExecutor executorService = new MyExecutor(1);
        List<InvokeTask> taskList = new ArrayList<InvokeTask>();
        for (int i = 0; i < 3; i++) {
            InvokeTask task = new InvokeTask(String.valueOf(i));
            taskList.add(task);
        }
        List<Future<Result>> futureList = new ArrayList();
        Task task = new Task("asd");
//        executorService.submit(new FutureTask(task,String.class));
        executorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.MILLISECONDS);
//        executorService.scheduleWithFixedDelay(task,1,100,TimeUnit.MILLISECONDS);
//            futureList = executorService.scheduleAtFixedRate(task,1,1,TimeUnit.SECONDS);
        executorService.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        executorService.shutdown();
        System.out.println("Main: printing all results");
        for (Future<Result> resultFuture : futureList) {
            try {
                Result result = resultFuture.get();
                System.out.println("Name: " + result.getName() + " and value: " + result.getValue());
//                executorService.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    public void test() {
        ScheduledThreadPoolExecutor executorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        executorService.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    }
}
