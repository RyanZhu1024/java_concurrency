package countDownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by shuxuan on 1/23/15.
 */
public class VideoConference implements Runnable{
    private final CountDownLatch countDownLatch;

    public VideoConference(int number) {
        countDownLatch = new CountDownLatch(number);
    }

    public void arrive(String name){
        System.out.println("name: "+name+" has arrived");
        countDownLatch.countDown();
        System.out.println("waiting for participants "+countDownLatch.getCount());
    }

    @Override
    public void run() {
        System.out.println("participants: "+countDownLatch.getCount());
        try{
            /**
             * to redo the this conference, try thread pool
             * no reset for countdownlatch, use cyclicBarrier
             */
            countDownLatch.await();
            System.out.println("all participants have arrived");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
