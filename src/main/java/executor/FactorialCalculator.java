package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/27/15.
 */
public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result=1;
        if(number==0||number==1) {
            result=1;
        }else{
            for(int i=2;i<=number;i++){
                result=result*i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.println("Current Thread: "+Thread.currentThread().getName()+", result: "+result);
        return result;
    }
}
