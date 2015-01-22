package producerconsumers;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition space;
    private Condition lines;

    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<String>();
        this.lock = new ReentrantLock();
        this.lines = lock.newCondition();
        this.space = lock.newCondition();
        this.pendingLines = true;
    }

    public void insert(String line) {
        this.lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("Thread: %s insert a line: size is: %d\n", Thread.currentThread().getName(), line.length());
            this.lines.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public String get() {
        String line = null;
        this.lock.lock();
        try {
            while (this.buffer.size() == 0 && hasPendingLines()) {
                this.lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
//                if(line==null){
//                    System.out.printf("Thread: %s read line: null\n", Thread.currentThread().getName());
//                }else {
                System.out.printf("Thread: %s read line: %d\n", Thread.currentThread().getName(), line.length());
//                }
                this.space.signalAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }
}
