package exchanger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Consumer implements Runnable {

    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.println("Consumer: " + cycle);
            try {
                this.buffer = this.exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Iterator<String> iterator = this.buffer.iterator();
            while (iterator.hasNext()) {
                System.out.println("Consumer message: " + iterator.next());
                iterator.remove();
            }
            cycle++;
        }
    }
}
