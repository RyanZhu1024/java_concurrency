package executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Server {
    private ThreadPoolExecutor threadPoolExecutor;

    public Server() {
//        this.threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5, Executors.defaultThreadFactory());
    }

    public void executeTask(Task task) {
        System.out.println("Server: a new Task arrived");
        this.threadPoolExecutor.execute(task);
//        System.out.println("Pool size: "+this.threadPoolExecutor.getPoolSize());
//        System.out.println("Active count: "+this.threadPoolExecutor.getActiveCount());
//        System.out.println("Server: completed tasks: "+this.threadPoolExecutor.getCompletedTaskCount());
    }

    public void endServer() {
        this.threadPoolExecutor.shutdown();
    }
}
