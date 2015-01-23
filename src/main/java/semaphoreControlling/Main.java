package semaphoreControlling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Main {
    public static void main(String[] args) {
        List<Thread> threadList=new ArrayList<Thread>();
        PrintQueue queue=new PrintQueue();
        for(int i=0;i<5;i++){
            threadList.add(new Thread(new Job(queue)));
        }
        for(Thread th:threadList){
            th.start();
        }
    }
}
