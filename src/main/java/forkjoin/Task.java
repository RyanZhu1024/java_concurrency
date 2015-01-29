package forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by shuxuan on 1/29/15.
 */
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrice();
        } else {
            int middle = (last + first) / 2;
//            System.out.println("Pending tasks: " + getQueuedTaskCount());
            System.out.println("Active thread count: "+Thread.activeCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            if (inForkJoinPool()) {
                invokeAll(t1, t2);//synchronous call, working thread will take other
                                    // tasks to execute and create new threads if necessary
                if(first==0&&last==10000) {
                    /*
                    current task will be suspended until all subtasks finished
                     */
                    System.out.println("in the forkjoinpool, two tasks for first: " + first +
                            " and middle: " + middle + " and last: " + last);
                    System.out.println("Current thread name: " + Thread.currentThread().getName());
                }
            }
        }
    }

    private void updatePrice() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
