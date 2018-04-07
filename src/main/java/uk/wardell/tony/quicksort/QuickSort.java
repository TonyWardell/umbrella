package uk.wardell.tony.quicksort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {

    public static void main(String[] args) {
        var input = Arrays.asList("sofa", "chair", "light", "amp", "piano", "television", "table");
        Comparator<String> comparator = Comparator.naturalOrder();
        QuickSort quickSort = new QuickSort();
        List<String> output = quickSort.sort(input, comparator);
        Stream.of(output).forEach(System.out::println);
    }

    List<String> sort(List<String> input, Comparator<String> comparator){
        Objects.requireNonNull(input);
        if(input.size() <= 1){
            return input;
        }

        int pivotPosition = positionBetweenLeftAndRight(input);
        String pivotValue = input.get(pivotPosition);

        List<String> lower = input.stream()
                .filter(e -> comparator.compare(e, pivotValue) < 0)
                .collect(Collectors.toList());

        List<String> upper = input.stream()
                .filter(e -> comparator.compare(e, pivotValue) >= 0)
                .collect(Collectors.toList());

        List<String> sortedLower = sort(lower, comparator);
        List<String> sortedUpper = sort(upper, comparator);

        var result = new ArrayList<String>();
        result.addAll(sortedLower);
        result.addAll(sortedUpper);
        return result;
    }

    private static Random rgen = new Random();

    // produce a random number in the left to right range
    private static int positionBetweenLeftAndRight(List<String> input){
        return Math.abs(rgen.nextInt()) % (input.size());
    }
}
