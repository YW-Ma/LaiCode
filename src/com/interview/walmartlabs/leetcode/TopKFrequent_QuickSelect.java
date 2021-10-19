package com.interview.walmartlabs.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class TopKFrequent_QuickSelect {
    // 由于只需要返回"top k freq" 而不需要按照一定的顺序，所以当然可以使用quick select了
    
    HashMap<Integer, Integer> count;
    int[] array;
    
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) corner case:
        if (nums.length == k) {
            return nums;
        }
        // O(N) count freq
        count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // O(N) quickSelect ,  worst case O(N^2) in each recursion layer we choose the worst pivot
        array = new int[count.size()];
        int i = 0;
        for (int n : count.keySet()) {
            array[i] = n;
            i++;
        }
        int n = array.length;
        quickSelect(0, n - 1, n - k);
        return Arrays.copyOfRange(array, n - k, n); // k is 1 based.
    }
    
    private void quickSelect(int left, int right, int k) {  // 【1】quick select 不要忘记base case
        // base case:
        if (left == right) {
            return;
        }
        
        // pick pivot
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        // do partition (get two side)
        pivotIndex = partition(left, right, pivotIndex);
        // recursively call on one side or return
        if (pivotIndex == k) {
            return;
        } else if (pivotIndex > k) {
            quickSelect(left, pivotIndex - 1, k);
        } else {
            quickSelect(pivotIndex + 1, right, k);
        }
    }
    
    private int partition(int left, int right, int pivotIndex) {
        swap(pivotIndex, right);
        int pivotFreq = count.get(array[right]);
        int writer = left; // partition要严格从left开始走到right。 不能从0开始
        for (int i = left; i <= right; i++) {
            if (count.get(array[i]) < pivotFreq) { // 这样右侧就会严格大于
                swap(writer, i);
                writer++;
            }
        }
        swap(writer, right);
        return writer;
    }
    
    private void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
