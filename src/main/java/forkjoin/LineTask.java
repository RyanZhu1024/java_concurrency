package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/30/15.
 */
public class LineTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private int start, end;
    private String word;
    private String[] line;

    public LineTask(int start, int end, String word, String[] line) {
        this.start = start;
        this.end = end;
        this.word = word;
        this.line = line;
    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 100) {
            result = count(line, start, end, word);
        } else {
            int mid = (end + start) / 2;
            LineTask task1 = new LineTask(start, mid, word, line);
            LineTask task2 = new LineTask(mid, end, word, line);
            invokeAll(task1,task2);
            try {
                result=groupResults(task1.get(),task2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("Line: at start "+start+" and end "+end+" has number "+result);
        return result;
    }

    private Integer count(String[] line, int start, int end, String word) {
        int counter=0;
        for(int i=start;i<end;i++){
            if(line[i].equals(word)){
                counter++;
            }
        }
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return counter;
    }

    private Integer groupResults(Integer number1, Integer number2) {
        return number1+number2;
    }
}
