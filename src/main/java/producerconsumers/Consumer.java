package producerconsumers;

import java.util.Random;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Consumer implements Runnable {
    private Buffer buffer;

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

        System.out.println("Right now processing the line: " + line);
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(32));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
