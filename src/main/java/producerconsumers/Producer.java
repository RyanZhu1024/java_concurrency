package producerconsumers;

/**
 * Created by shuxuan on 1/22/15.
 */
public class Producer implements Runnable {
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while(mock.hasMoreLines()){
            buffer.insert(mock.getLine());
        }
        buffer.setPendingLines(false);
    }
}
