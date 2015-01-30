package completionService;

import java.util.concurrent.CompletionService;

/**
 * Created by shuxuan on 1/29/15.
 */
public class ReportRequest implements Runnable{
    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator=new ReportGenerator(name,"report");
        service.submit(reportGenerator);
    }
}
