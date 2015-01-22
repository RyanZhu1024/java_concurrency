package producerconsumers;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Main {
    public static void main(String[] args) {
        FileMock fileMock=new FileMock(100,10);
        Buffer buffer=new Buffer(20);
        Producer producer=new Producer(fileMock,buffer);
        Consumer[] consumers=new Consumer[3];
        Thread threadProducer=new Thread(producer, "producer");
        Thread[] threadConsumers=new Thread[3];

        for(int i=0;i<3;i++){
            consumers[i]=new Consumer(buffer);
            threadConsumers[i]=new Thread(consumers[i],"consumer"+i);
        }

        threadProducer.start();
        for(int i=0;i<3;i++){
            threadConsumers[i].start();
        }
    }
}
