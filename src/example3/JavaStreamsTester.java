package example3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamsTester {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("test");
        strings.add("POS");
        strings.add("");
        strings.add("123");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        System.out.println(getCountEmptyString(strings));
        System.out.println(getCountLength3(strings));
        List<String> deleted = deleteEmptyStrings(strings);
        for(String s : deleted) {
            System.out.println(s);
        }
        System.out.println(getMergedString(strings, ";"));
        List<Integer> squares = getSquares(numbers);
        for(Integer i : squares) {
            System.out.println(i);
        }
        System.out.println(getMax(numbers));
        System.out.println(getMin(numbers));
        System.out.println(getSum(numbers));
        System.out.println(getAverage(numbers));
    }

    private static int getCountEmptyString(List<String> strings) {
        return strings.stream().filter(string -> string.isEmpty()).mapToInt(string -> 1).reduce(0, (s1, s2) -> s1 + s2);
    }

    private static int getCountLength3(List<String> strings) {
        return strings.stream().filter(string -> string.length() == 3).mapToInt(string -> 1).reduce(0, (s1, s2) -> s1 + s2);
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String separator) {
        Optional<String> merged = strings.stream().reduce((s1, s2) -> s1 + separator + s2);
        return merged.get();
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().map(x -> x * x).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).max().orElseThrow(NoSuchElementException::new);
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).min().orElseThrow(NoSuchElementException::new);
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).sum();
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream().mapToInt(i -> i).average().orElseThrow(NoSuchElementException::new);
    }
}
