package forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by shuxuan on 1/30/15.
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
    private String extension;
    private String path;

    public FolderProcessor(String extension, String path) {
        this.extension = extension;
        this.path = path;
    }

    @Override
    protected List<String> compute() {
        List<FolderProcessor> tasks=new ArrayList<FolderProcessor>();
        List<String> result=new ArrayList<String>();
        File file=new File(this.path);
        File[] content=file.listFiles();
        if(content!=null){
            for(int i=0;i<content.length;i++){
                if(content[i].isDirectory()){
                    FolderProcessor task=new FolderProcessor(content[i].getAbsolutePath(),this.extension);
                    task.fork();// asynchronous call
                    tasks.add(task);
                }else{
                    if(checkFile(content[i].getName())){
                        result.add(content[i].getAbsolutePath());
                    }
                }
            }
        }
        if(result.size()>50){
            System.out.println("More than 50 tasks is in the queue: "+tasks.size());
        }
        addResultsFromTasks(result,tasks);
        return result;
    }

    private void addResultsFromTasks(List<String> result, List<FolderProcessor> tasks) {
        for(FolderProcessor ta:tasks){
            result.addAll(ta.join());
        }
    }

    private boolean checkFile(String name) {
        return name.endsWith(this.extension);
    }
}
