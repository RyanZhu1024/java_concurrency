package producerconsumers;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantReadWriteLock lock;
    private Condition space;
    private Condition lines;

    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<String>();
        this.lock = new ReentrantReadWriteLock();
        this.space = lock.writeLock().newCondition();
        this.pendingLines = true;
    }

    public void insert(String line) {
        this.lock.writeLock().lock();
        try {
            buffer.offer(line);
            System.out.printf("Thread: %s insert a line: size is: %d\n", Thread.currentThread().getName(), line.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public String get() {
        String line = null;
        this.lock.readLock().lock();
        try {
            if (hasPendingLines()) {
                this.lock.readLock().unlock();
                this.lock.writeLock().lock();
                line = buffer.poll();
                this.lock.writeLock().unlock();
                this.lock.readLock().lock();
                if(line!=null) {
                    System.out.printf("Thread: %s read line: %d\n", Thread.currentThread().getName(), line.length());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.readLock().unlock();
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
