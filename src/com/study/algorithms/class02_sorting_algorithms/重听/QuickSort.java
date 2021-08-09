package com.study.algorithms.class02_sorting_algorithms.重听;

import java.util.*;

public class QuickSort {
    // Time Complexity: avg case: O(nlogn), O(n^2) in worst case (very unlucky)
    // Space Complexity: avg case: O(# of recursion depth) = O(logn),  but O(n) in worst case


    // [2,9,7,3,20]
    //    p
    // 分区:
    // [2,3,7,20,9]
    //  l
    //         r
    //  before left, smaller than pivot
    //  after right, larger or equal than pivot
    //  l, next element to evaluate

    public int[] quickSort(int[] array) {
        // corner case:
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int leftBorder, int rightBorder) {
        // base case: one element or no element
        if (leftBorder >= rightBorder) {
            return;
        }

        // step 1: partition --> return the final pivot index, i.e. the final position of "l"
        // define a pivot and use it to partition the array (we need the new index of pivot)
        int pivot = partition(array, leftBorder, rightBorder);
        // step 2: recursive call
        // recursively sort the two sub arrays
        quickSort(array, leftBorder, pivot);
        quickSort(array, pivot + 1, rightBorder);
        return;
    }

    private int partition(int[] array, int leftBorder, int rightBorder) {
        int pivotIndex = getPivotIndex(array, leftBorder, rightBorder);
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, rightBorder);
        int l = leftBorder;
        int r = rightBorder - 1;
        while (l <= r) { // while there are "white" remaining in the array;
            // l==r的时候，还是要比较一次，这样能保证l最后站立在大于等于pivot的那边。
            if (array[l] >= pivotValue) {
                swap(array, l, r);
                r--;
            } else {
                l++;
            }
        }
        swap(array, l, rightBorder);
        return l;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private int getPivotIndex(int array[], int leftBorder, int rightBorder) {
        Random random = new Random();

        int p1 = random.nextInt(rightBorder - leftBorder + 1) + leftBorder;
        int p2 = random.nextInt(rightBorder - leftBorder + 1) + leftBorder;
        int p3 = random.nextInt(rightBorder - leftBorder + 1) + leftBorder;

        // 如果p1是中位数，那么有 (p2-p1)(p3-p1) <= 0, 否则必然是一个大于零的值
        if ((array[p2] - array[p1]) * (array[p3] - array[p1]) <= 0) {
            return p1;
        } else if ((array[p1] - array[p2]) * (array[p3] - array[p2]) <= 0) {
            return p2;
        } else {
            return p3;
        }
    }
}
