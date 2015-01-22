package producerconsumers;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Consumer implements Runnable {
    private Buffer buffer;
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static ConcurrentSkipListSet<String> listSet = new ConcurrentSkipListSet<String>();

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        if (line != null) {
            System.out.println("Right now processing the line: " + line);
            System.out.println(atomicInteger.incrementAndGet());
            listSet.add(line);
            System.out.println("All elements got from the File Mock currently: " + listSet.size());
        }
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(32));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
