package example2;

public class Task implements Runnable {
    private int firstNumber;
    private int lastNumber;
    private int result;

    public Task(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for(int i = firstNumber; i <= lastNumber; i++) {
            result += i;
        }
    }
}


