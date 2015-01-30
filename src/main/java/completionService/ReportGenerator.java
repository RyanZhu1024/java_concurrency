package completionService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/29/15.
 */
public class ReportGenerator implements Callable<String> {
    private String sender;
    private String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() {
        long duration= (long) (Math.random()*1);
        try {
            System.out.println("Generating a report in "+duration+" seconds");
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ret=sender+": "+title;
        return ret;
    }
}
