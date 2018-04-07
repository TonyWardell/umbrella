package uk.wardell.tony.interviewsort;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * Examples of different ways of sorting an array of numbers in to most to least frequent.
 */

public class NumberListSort {

    static int[] testNumbers = new int[]{3, 7, 5, 8, 3, 6, 1, 3, 3, 9, 0, 5, 5, 4, 2, 6};

    public static void main(String[] args) {
        NumberListSort numberListSort = new NumberListSort();

        List<Integer> numbers = Arrays.stream(testNumbers).boxed().collect(toList());

        List<NumberFrequency> result = numberListSort.sortC(numbers);
        result.stream().forEach(entry -> System.out.println(entry));
    }

    public List<NumberFrequency> sortA(List<Integer> args) {
        Map<Integer, Long> frequencyResult = args.stream()
                .collect(groupingBy(v -> v, counting()));

        List<NumberFrequency> mostFrequentFirst = frequencyResult.entrySet()
                .stream()
                .map(e -> new NumberFrequency(e.getKey(), e.getValue()))
                .sorted((f1,f2) -> Long.compare(f2.frequency, f1.frequency))
                .collect( toList());
        return mostFrequentFirst;
    }

    List<NumberFrequency> sortB(List<Integer> args) {
        Map<Integer, Long> frequencyResult = args.stream()
                .collect(groupingBy(v -> v, counting()));

        List<NumberFrequency> numberFrequencies = frequencyResult.entrySet()
                .stream()
                .map(e -> new NumberFrequency(e.getKey(), e.getValue()))
                .collect( toList());

        numberFrequencies.sort(comparingLong(NumberFrequency::getFrequency).reversed());
        return numberFrequencies;
    }

    private List<NumberFrequency> sortC(List<Integer> args) {
        Map<Integer, Long> frequencyResult = args.stream()
                .collect(groupingBy(v -> v, counting()));

        return frequencyResult.entrySet()
                .stream()
                .map(e -> new NumberFrequency(e.getKey(), e.getValue()))
                .sorted(comparingLong(NumberFrequency::getFrequency).reversed())
                .collect( toList());
    }

    static class NumberFrequency {
        Integer number;
        long frequency;

        public NumberFrequency(Integer number, long frequency) {
            this.number = number;
            this.frequency = frequency;
        }

        public long getFrequency() {
            return frequency;
        }

        @Override
        public String toString() {
            return "NumberFrequency{" +
                    "number=" + number +
                    ", frequency=" + frequency +
                    '}';
        }
    }
}
