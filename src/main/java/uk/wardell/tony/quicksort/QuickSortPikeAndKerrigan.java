package uk.wardell.tony.quicksort;

import java.util.Random;
import java.util.stream.Stream;

public class QuickSortPikeAndKerrigan {

    public static void main(String[] args) {
        String[] input = new String [] {"sofa", "chair", "light", "amp", "piano", "television", "table"};

        sort(input, 0, input.length-1, new Scmp());

        Stream.of(input).forEach(e -> System.out.println(e));
    }

    //Quicksort.sort: quicksort v[left]..v[right]
    static void sort(Object[] objArray, int left, int right, Cmp cmp){
        int i, last;

        if(left >= right) //nothing to do
            return;

        //Move pivot element to to v[left] partition
        swap(objArray, left, positionBetweenLeftAndRight(left,right));
        last = left;

        for(i = left + 1; i <= right; i++){
            if(cmp.cmp(objArray[i], objArray[left]) < 0)
                swap(objArray, ++last, i);
        }

        //restore pivot element
        swap(objArray, left, last);

        //recursively sort each part
        sort(objArray, left, last-1, cmp);
        sort(objArray, last+1, right, cmp);
    }

    static void swap(Object[] v, int i, int j){
        Object temp = v[i];
        v[i] = v[j];
        v[j] = temp;

    }

    static Random rgen = new Random();

    // produce a random number in the left to right range
    static int positionBetweenLeftAndRight(int left, int right){
        return left + Math.abs(rgen.nextInt()) % (right-left+1);
    }

    interface Cmp {
        int cmp(Object x, Object y);
    }

    static class Scmp implements Cmp {

        @Override
        public int cmp(Object x, Object y) {
            String s1 = (String) x;
            String s2 = (String) y;
            return s1.compareTo(s2);
        }
    }
}
