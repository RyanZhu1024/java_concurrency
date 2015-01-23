package semaphoreControlling;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shuxuan on 1/23/15.
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean freePrinters[];
    private Lock printerLock;

    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters=new boolean[3];
        printerLock=new ReentrantLock();
        for(int i=0;i<3;i++){
            freePrinters[i]=true;
        }
    }

    public void printJob(Object job){
        try {
            semaphore.acquire();
            int assignedPrinter=getPrinterIndex();
            System.out.println("printing a job for " + job.toString());
//            TimeUnit.SECONDS.sleep(1);
            freePrinters[assignedPrinter]=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getPrinterIndex() {
        int ret=-1;
        try {
            printerLock.lock();
            for (int i = 0; i < 3; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }finally {
            printerLock.unlock();
        }
        return ret;
    }
}
