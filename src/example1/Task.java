package example1;

import java.util.List;

public class Task implements Runnable{
    private List<Integer> numberList;
    private int divider;

    public Task(List<Integer> numberList, int divider) {
        this.numberList = numberList;
        this.divider = divider;
    }

    @Override
    public void run() {
        for(int i : numberList) {
            if(i % divider == 0 && i != 0) {
                System.out.println(i);
            }
        }
    }
}

