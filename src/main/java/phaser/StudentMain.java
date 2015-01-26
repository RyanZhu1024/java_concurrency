package phaser;

/**
 * Created by shuxuan on 1/26/15.
 */
public class StudentMain {
    public static void main(String[] args) throws InterruptedException {
        MyPhaser myPhaser=new MyPhaser();
        Student[] students=new Student[5];
        for(int i=0;i<5;i++){
            students[i]=new Student(myPhaser);
            myPhaser.register();
        }
        Thread[] threads=new Thread[5];
        for(int i=0;i<5;i++){
            threads[i]=new Thread(students[i]);
            threads[i].start();
        }
        for(int i=0;i<5;i++){
            threads[i].join();
        }

        System.out.println("All threads have been terminated");
    }
}
