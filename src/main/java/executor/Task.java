package executor;

import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name+" is running");
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
