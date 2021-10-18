package example2;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n> ");
        int n = scanner.nextInt();
        int numberOfThreads = (int) Math.ceil(n/100.0);;

        ThreadPoolExecutor executor = (ThreadPoolExecutor)  Executors.newFixedThreadPool(numberOfThreads);

        int sum = 0;
        int i = 1;
        if(n >= 100) {
            for (i = 1; i+99 <= n; i += 100) {
                Task task = new Task(i, (i + 99));
                executor.execute(task);
                Future<Integer> sumOfOneTask = task.calculate(executor);
                try {
                    sum += sumOfOneTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        if((i-1) != n) {
            Task task = new Task(i, n);
            executor.execute(task);
            Future<Integer> sumOfOneTask = task.calculate(executor);
            try {
                sum += sumOfOneTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        System.out.println("Sum: " + sum);
    }
}
