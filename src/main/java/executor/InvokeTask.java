package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/28/15.
 */
public class InvokeTask implements Callable<Result> {
    private String name;

    public InvokeTask(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.println("Task " + this.name + " started");
        long duration = (long) Math.random() * 10;
        TimeUnit.SECONDS.sleep(duration);
        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += Math.random() * 100;
        }
        Result result=new Result();
        result.setName(this.name);
        result.setValue(value);
        return result;
    }
}
