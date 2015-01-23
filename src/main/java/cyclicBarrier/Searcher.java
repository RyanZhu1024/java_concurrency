package cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Searcher implements Runnable {

    private int firstRow;
    private int lastRow;

    private MatrixMock matrixMock;
    private Result result;

    private int number;
    private CyclicBarrier cyclicBarrier;

    public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Result result, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.result = result;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        int counter;
        System.out.println("processing line from "+this.firstRow+" to "+this.lastRow);
        for(int i=this.firstRow;i<this.lastRow;i++){
            int row[]=matrixMock.getRow(i);
            if(row!=null){
                counter=0;
                for(int j=0;j<row.length;j++){
                    if(row[j]==number){
                        counter++;
                    }
                }
                result.setData(i,counter);
            }
        }
        System.out.println("processing finished in "+Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
