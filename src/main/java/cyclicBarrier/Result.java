package cyclicBarrier;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Result {
    private int[] data;

    public Result(int size) {
        this.data = new int[size];
    }


    public void setData(int position,int value) {
        this.data[position]=value;
    }

    public int[] getData() {
        return data;
    }
}
