package completionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by shuxuan on 1/29/15.
 */
public class ReportProcessor implements Runnable {
    private CompletionService<String> service;

    public void setEnd(boolean end) {
        this.end = end;
    }

    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while(!end){
            Future<String> future=service.poll();
            if(future!=null){
                try {
                    String result=future.get();
                    System.out.println("Report Received: "+result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
