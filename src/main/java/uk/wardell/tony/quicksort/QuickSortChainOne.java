package uk.wardell.tony.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://gist.github.com/chainone/b7f41152db5d711e110e1c73a4019ae8
 */

public class QuickSortChainOne {
    private static Function<Integer, Predicate<Integer>> smallerThan = x -> y -> y < x;

    public static List<Integer> qsort(List<Integer> l) {
        if (l.isEmpty()) return new ArrayList<>();

        return Stream.concat(Stream.concat(
                qsort(l.stream()
                        .skip(1)
                        .filter(smallerThan.apply(l.get(0)))
                        .collect(Collectors.toList()))
                        .stream(),
                Stream.of(l.get(0))),
                qsort(l.stream()
                        .skip(1)
                        .filter(smallerThan.apply(l.get(0)).negate())
                        .collect(Collectors.toList()))
                        .stream())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(5, 6, 7, 23, 4, 5645, 6, 1223, 44453, 60182, 2836, 23993, 1);
        System.out.println(qsort(l));
    }
}