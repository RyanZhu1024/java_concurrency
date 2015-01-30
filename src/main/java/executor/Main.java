package executor;

/**
 * Created by shuxuan on 1/27/15.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Name: " + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
