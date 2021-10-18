package example2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Task implements Callable<Integer> {
    private int firstNumber;
    private int lastNumber;

    public Task(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for(int i = firstNumber; i <= lastNumber; i++) {
            result += i;
        }
        return result;
    }
}


