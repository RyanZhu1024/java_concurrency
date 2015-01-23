package cyclicBarrier;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Grouper implements Runnable{

    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult=0;
        System.out.println("processing results---------");
        int data[]=result.getData();
        for(int number:data){
            finalResult+=number;
        }
        System.out.println("printing result: "+finalResult);
    }
}
