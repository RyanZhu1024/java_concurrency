package phaser;

import java.util.concurrent.Phaser;

/**
 * Created by shuxuan on 1/26/15.
 */
public class Main {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(3);
        FileSearch fileSearch1=new FileSearch("/var/log","log",phaser);
        FileSearch fileSearch2=new FileSearch("/var/log","log",phaser);
        FileSearch fileSearch3=new FileSearch("/var/log","log",phaser);
        Thread th1=new Thread(fileSearch1,"th1");
        th1.start();
        Thread th2=new Thread(fileSearch2,"th2");
        th2.start();
        Thread th3=new Thread(fileSearch3,"th3");
        th3.start();
        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All threads terminated: "+phaser.isTerminated());
    }
}
