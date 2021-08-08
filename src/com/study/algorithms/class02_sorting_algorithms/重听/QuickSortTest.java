package com.study.algorithms.class02_sorting_algorithms.重听;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void quickSort() {
        QuickSort quickSort = new QuickSort();
        int[] res = quickSort.quickSort(new int[]{2,1});
        System.out.println(Arrays.toString(res));
    }
}