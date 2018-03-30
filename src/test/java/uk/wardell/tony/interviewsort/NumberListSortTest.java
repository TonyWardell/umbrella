package uk.wardell.tony.interviewsort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NumberListSortTest {

    int[] testNumbers = new int[] {3,7,8,3,1,9,0,5,5,4,2,6};


    @Test
    public void count(){
        NumberListSort numberListSort = new NumberListSort();

        List<Integer> numbers = Arrays.stream(testNumbers).boxed().collect(Collectors.toList());

        List<NumberListSort.NumberFrequency> result = numberListSort.sortB(numbers);
        System.out.println(result);
    }

}