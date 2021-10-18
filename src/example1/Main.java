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

        ThreadPoolExecutor executor = (ThreadPoolExecutor)  Executors.newFixedThreadPool(chunks);

        int listSize = numberList.size() / chunks;

        List<List<Integer>> allLists = new ArrayList<>();
        for (int i = 0; i < numberList.size(); i += listSize) {
            allLists.add(new ArrayList<Integer>(numberList.subList(i, Math.min(numberList.size(), i + listSize))));
        }

        for(int i = 0; i < chunks; i++) {
            Task task = new Task(allLists.get(i), divider);
            executor.execute(task);
        }
        executor.shutdown();

        /*int x = 0;
        for(List<Integer> liste : allLists) {
            System.out.println(x + ": " + liste.size());
            x++;
        }
        System.out.println(numberList.size());*/
    }
}
