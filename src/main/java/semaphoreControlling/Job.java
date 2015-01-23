package semaphoreControlling;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue queue) {
        this.printQueue=queue;
    }

    @Override
    public void run() {
        System.out.println("Ready for printing job for thread: "+Thread.currentThread().getName());
        this.printQueue.printJob(new Object());
        System.out.println("Finish printing job for thread: "+Thread.currentThread().getName());
    }
}
