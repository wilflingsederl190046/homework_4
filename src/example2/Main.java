package example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n> ");
        int n = scanner.nextInt();
        int numberOfThreads = (int) Math.ceil(n/100.0);;

        ThreadPoolExecutor executor = (ThreadPoolExecutor)  Executors.newFixedThreadPool(numberOfThreads);

        int sum = 0;
        int i = 1;
        List<Callable<Integer>> taskList = new ArrayList<>();
        if(n >= 100) {
            for (i = 1; i+99 <= n; i += 100) {
                Callable<Integer> task = new Task(i, (i + 99));
                taskList.add(task);
            }
        }
        if((i-1) != n) {
            Callable<Integer> task = new Task(i, n);
            taskList.add(task);
        }

        List<Future<Integer>> resultList = null;

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        for (int y = 0; y < resultList.size(); y++) {
            Future<Integer> future = resultList.get(y);
            try {
                sum += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum: " + sum);
    }
}
