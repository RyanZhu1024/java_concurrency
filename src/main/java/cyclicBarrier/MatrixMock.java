package cyclicBarrier;

import java.util.Random;

/**
 * Created by shuxuan on 1/23/15.
 */
public class MatrixMock {
    private int data[][];


    public MatrixMock(int size, int length, int number) {
        int count=0;
        data=new int[size][length];
        Random random=new Random();
        for(int i=0;i<size;i++){
            for(int j=0;j<length;j++){
                data[i][j]=random.nextInt(10);
                if(data[i][j]==number){
                    count++;
                }
            }
        }
        System.out.println("there are "+count+" occurrences of "+number+" in data");
    }

    public int[] getRow(int row){
        if(row>=0&&row<data.length){
            return data[row];
        }
        return null;
    }
}
