package example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Task implements Runnable {
    private int firstNumber;
    private int lastNumber;
    private int result;

    public Task(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    @Override
    public void run() {
        for(int i = firstNumber; i <= lastNumber; i++) {
            result += i;
        }
    }

    public Future<Integer> calculate(ExecutorService executor) {
        return executor.submit(() -> {
            Thread.sleep(10);
            return this.result;
        });
    }
}


