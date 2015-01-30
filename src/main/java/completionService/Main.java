package completionService;

import java.util.concurrent.*;

/**
 * Created by shuxuan on 1/29/15.
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        CompletionService<String> completionService=new ExecutorCompletionService<String>(executorService);
        ReportRequest r1=new ReportRequest("Face",completionService);
        ReportRequest r2=new ReportRequest("Online",completionService);
        ReportProcessor r3=new ReportProcessor(completionService);
//        Thread thread1=new Thread(r1);
//        Thread thread2=new Thread(r2);
//        Thread thread3=new Thread(r3);
//        thread1.start();
//        thread2.start();
//        thread3.start();
        ExecutorService mainService=Executors.newCachedThreadPool();
//        mainService.execute(r1);
//        mainService.execute(r2);
//        mainService.execute(r3);
        mainService.submit(r1);
        mainService.submit(r2);
        mainService.submit(r3);

//        try {
//            System.out.println("Waiting for the requests");
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Main shutting down");
        executorService.shutdown();//当executor service 在不同的线程里被shutdown同时有submitted tasks，
        // 会导致线程挂起，并且不会抛出rejected exception
//        mainService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
//            mainService.awaitTermination(5,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r3.setEnd(true);
        System.out.println("Main ends");
    }
}
