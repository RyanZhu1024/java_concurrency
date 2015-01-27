package exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Main {
    public static void main(String[] args) {
        List<String> consumerList=new ArrayList<String>();
        List<String> producerList=new ArrayList<String>();
        Exchanger<List<String>> exchanger=new Exchanger<List<String>>();

        Producer producer=new Producer(producerList,exchanger);
        Consumer consumer=new Consumer(consumerList,exchanger);

        Thread proTh=new Thread(producer);
        Thread conTh=new Thread(consumer);

        proTh.start();
        conTh.start();
    }
}
