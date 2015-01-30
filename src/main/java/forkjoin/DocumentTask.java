package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by shuxuan on 1/30/15.
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private String[][] document;
    private int start;
    private int end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (end - start < 10) {
            result = processLine(document, start, end, word);
        } else {
            int mid = (end + start) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int groupResults(Integer number1, Integer number2) {
        return number1 + number2;
    }

    private int processLine(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<LineTask>();
        for (int i = start; i < end; i++) {
            String[] line = document[i];
            LineTask lineTask = new LineTask(0, line.length, word, line);
            tasks.add(lineTask);
        }
        invokeAll(tasks);
        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            try {
                result += tasks.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
