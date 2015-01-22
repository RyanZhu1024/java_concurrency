package producerconsumers;

/**
 * Created by shuxuan on 1/22/15.
 */
public class FileMock {
    private String content[];
    private int index;

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                stringBuilder.append((char) indice);
            }
            content[i] = stringBuilder.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock" + (content.length - index));
            return content[index++];
        }
        return null;
    }
}
