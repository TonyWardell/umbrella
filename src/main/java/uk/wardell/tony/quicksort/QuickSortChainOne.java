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
    private static Function<String, Predicate<String>> smallerThan = x -> y -> y.compareTo(x) < 0;

    static Function<List<String>, List<String>> lowerStream = ll -> ll.stream()
            .skip(1)
            .filter(smallerThan.apply(ll.get(0)))
            .collect(Collectors.toList());

    static Function<List<String>, List<String>> upperStream = lu -> lu.stream()
            .skip(1)
            .filter(smallerThan.apply(lu.get(0)).negate())
            .collect(Collectors.toList());

    public static List<String> qsort(List<String> l) {
        if (l.isEmpty()) return new ArrayList<>();

        return Stream.concat(
                                Stream.concat(qsort(lowerStream.apply(l)).stream(), Stream.of(l.get(0))),
                                qsort(upperStream.apply(l)).stream()
                            ).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        List<Integer> l = Arrays.asList(5, 6, 7, 23, 4, 5645, 6, 1223, 44453, 60182, 2836, 23993, 1);
//        System.out.println(qsort(l));
//    }

    public static void main(String[] args) {
        var input = Arrays.asList("sofa", "chair", "light", "amp", "piano", "television", "table");
        List<String> output = QuickSortChainOne.qsort(input);
        Stream.of(output).forEach(System.out::println);
    }
}