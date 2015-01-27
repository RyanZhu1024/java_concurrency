package phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuxuan on 1/26/15.
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String end;// suffix
    private Phaser phaser;
    private List<String> results;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        this.results = new ArrayList<String>();
    }

    private void directoryProcess(File file) {
        File fileList[] = file.listFiles();
        if (fileList != null) {
            for (File fl : fileList) {
                if (fl.isDirectory()) {
                    directoryProcess(fl);
                } else {
                    fileProcess(fl);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(this.end)) {
            this.results.add(file.getAbsolutePath());
        }
    }

    private void filterResults() {
        List<String> listResult = new ArrayList<String>();
        long actualDate = new Date().getTime();
        for (String filePath : results) {
            File tempFile = new File(filePath);
            long fileDate = tempFile.lastModified();
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                listResult.add(filePath);
            }
        }
        this.results = listResult;
    }

    private boolean checkResult() {
        if (this.results.isEmpty()) {
            System.out.println("Thread: " + Thread.currentThread().getName() + " finished in phase: " + this.phaser.getPhase());
            this.phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.println("Thread: " + Thread.currentThread().getName() + " in phase " + this.phaser.getPhase() + " with results: " + this.results.size());
            this.phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for(String filePath:this.results){
            File file=new File(filePath);
            System.out.println("Thread: "+Thread.currentThread().getName()+" for file: "+file.getAbsolutePath());
        }
        this.phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        this.phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+" starting searching");
        File file=new File(this.initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResult()){
            return;
        }
        filterResults();
        if(!checkResult()){
            return;
        }
        showInfo();
        this.phaser.arriveAndDeregister();
        System.out.println("Thread: "+Thread.currentThread().getName()+" finished work!!!");
    }
}
