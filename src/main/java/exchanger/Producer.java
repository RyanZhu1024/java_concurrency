package exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle=1;
        for(int i=0;i<10;i++){
            System.out.println("Producer: Cycle "+cycle);
            for(int j=0;j<10;j++){
                String message="Event: "+((i*10)+j);
                System.out.println("Producer: message "+message);
                this.buffer.add(message);
            }
            try {
                /*
                transfer this buffer to another thread as the parameter
                receive another thread's buffer as a return
                 */
                this.buffer=this.exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cycle++;
        }
    }
}
