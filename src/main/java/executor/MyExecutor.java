package executor;

import java.util.concurrent.*;

/**
 * Created by shuxuan on 1/28/15.
 */
public class MyExecutor extends ScheduledThreadPoolExecutor {
    public MyExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public MyExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public MyExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public MyExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return super.scheduleAtFixedRate(wrapCommand(command), initialDelay, period, unit);
    }

    private Runnable wrapCommand(Runnable command){
        return new LogOnExceptionRunnable(command);
    }

    private class LogOnExceptionRunnable implements Runnable {
        private Runnable command;
        public LogOnExceptionRunnable(Runnable command) {
            super();
            this.command=command;
        }

        @Override
        public void run() {
            try {
                command.run();
            }catch(Exception ex){
                System.out.println("Error running");
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
}
