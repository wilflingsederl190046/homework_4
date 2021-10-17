package example1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numberList = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new FileReader("numbers.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                for(int i = 0; i < parts.length; i++) {
                    if(parts[i].matches("-?\\d+(\\.\\d+)?")) {
                        numberList.add(Integer.parseInt(parts[i].trim()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("chunks> ");
        int chunks = scanner.nextInt();
        System.out.print("divider> ");
        int divider = scanner.nextInt();

        double numberOfThreads = (double) numberList.size() / chunks;
        numberOfThreads = Math.ceil(numberOfThreads);
        ThreadPoolExecutor executor = (ThreadPoolExecutor)  Executors.newFixedThreadPool((int) numberOfThreads);

        Map<Integer, List<Integer>> allLists = new TreeMap<>();
        int y = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numberList.size(); i++) {
            list.add(numberList.get(i));
            if (i % chunks == 0 || i == numberList.size()-1) {
                allLists.put(y, list);
                list.clear();
                y++;
            }
        }

        for(int i = 0; i < numberOfThreads; i++) {
            Task task = new Task(allLists.get(i), divider);
            /*System.out.println("created: " + i);*/
            executor.execute(task);
        }
        executor.shutdown();

        int x = 0;
        for(List<Integer> liste : allLists.values()) {
            System.out.println(x + ": " + liste.size());
            x++;
        }
        System.out.println(numberList.size());
    }
}
