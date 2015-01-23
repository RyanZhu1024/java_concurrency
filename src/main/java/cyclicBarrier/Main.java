package cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Main {
    public static void main(String[] args) {
        final int ROWS = 1000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANTS = 2000;

        MatrixMock matrixMock = new MatrixMock(ROWS, NUMBERS, SEARCH);
        Result result = new Result(ROWS);
        Grouper grouper = new Grouper(result);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, grouper);
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANTS, (i * LINES_PARTICIPANTS) +
                    LINES_PARTICIPANTS, matrixMock, result, 5, cyclicBarrier);
            Thread thread=new Thread(searchers[i]);
            thread.start();
        }
        System.out.println("Main thread finished");
    }
}
