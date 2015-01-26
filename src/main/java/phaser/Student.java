package phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/26/15.
 */
public class Student implements Runnable {

    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }


    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.println("Thread: " + Thread.currentThread().getName() + " is doing exam1");
        doExam1();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Thread: " + Thread.currentThread().getName() + " is doing exam2");
        doExam2();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Thread: " + Thread.currentThread().getName() + " is doing exam3");
        doExam3();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Thread: " + Thread.currentThread().getName() + " finished all");
    }

    private void doExam3() {
        long duration = (long) Math.random() * 10;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExam2() {
        long duration = (long) Math.random() * 10;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExam1() {
        long duration = (long) Math.random() * 10;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
