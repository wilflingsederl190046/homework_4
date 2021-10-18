package example2;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n> ");
        int n = scanner.nextInt();
        double numberOfThreads = (n/100);
        Math.ceil(numberOfThreads);

        ThreadPoolExecutor executor = (ThreadPoolExecutor)  Executors.newFixedThreadPool((int) numberOfThreads);

        int sum = 0;
        int i;
        for(i = 1; i <= n; i += 100) {
            Task task = new Task(i, (i+99));
            executor.execute(task);
            sum += task.getResult();
        }

        if((i-1) != n) {
            Task task = new Task(i, n);
            executor.execute(task);
            sum += task.getResult();
        }
        executor.shutdown();

        System.out.println("Sum: " + sum);
    }
}
