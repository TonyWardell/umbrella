package uk.wardell.tony.quicksort;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSortVerB {

    public static void main(String[] args) {
        var input = Arrays.asList("sofa", "chair", "light", "amp", "piano", "television", "table");
        Comparator<String> comparator = Comparator.naturalOrder();
        QuickSortVerB quickSort = new QuickSortVerB();
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

        Predicate<String> isLower = e -> comparator.compare(e, pivotValue) < 0;
        Predicate<String> isHigher = e -> comparator.compare(e, pivotValue) >= 0;

        List<String> sortedLower = sort(beyondBoundary(input, isLower), comparator);
        List<String> sortedUpper = sort(beyondBoundary(input, isHigher), comparator);

        var result = new ArrayList<>(sortedLower);
        result.addAll(sortedUpper);
        return Collections.unmodifiableList(result);
    }

    private List<String> beyondBoundary(List<String> input, Predicate<String> isInRange) {
        return input.stream()
                .filter(e -> isInRange.test(e))
                .collect(Collectors.toList());
    }

    private static Random rgen = new Random();

    // produce a random number in the left to right range
    private static int positionBetweenLeftAndRight(List<String> input){
        return Math.abs(rgen.nextInt()) % (input.size());
    }
}
