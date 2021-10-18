package com.interview.walmartlabs.leetcode;

import java.util.Random;

public class KthLargest_QuickSelect {
    // quick select: O(N)
    // 计算方法，设算法TC为T(n)
    
    // T(n) = cn + T(n/2)  每轮和n个元素比较
    // T(n/2) = c(n/2) + T(n/4)
    // ...
    // T(2) = 2c + T(1)
    // T(1) = c
    
    // sum up --> T(n) = c(n + n/2 + ... + 1) = 2n = O(n)
    
    int[] nums;
    
    public int findKthLargest(int[] nums, int k) { // k starts from 1.
        this.nums = nums;
        return quickSelect(0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int left, int right, int pos) {
        // base case: only one element
        if (left == right) {
            return this.nums[left];
        }
        
        // recursion rule:
        Random random = new Random();
        int pivotPos = left + random.nextInt(right - left + 1);
        pivotPos = partition(left, right, pivotPos);
        if (pivotPos == pos) {
            return this.nums[pivotPos];
        } else if (pivotPos > pos) {
            return quickSelect(left, pivotPos - 1, pos);
        } else {
            return quickSelect(pivotPos + 1, right, pos);
        }
    }
    
    private int partition(int left, int right, int pivotPos) {
        int pivot = this.nums[pivotPos];
        // move pivot to the right
        swap(pivotPos, right); // 放到最后面不参与quick select
        // move all values smaller than pivot to the left
        int writer = left;
        for (int reader = left; reader < right; reader++) {
            if (this.nums[reader] < pivot) {
                swap(reader, writer);
                writer++;
            }
        }
        
        // 另一个move的方法是从两边开始，上方的方法是都从左边开始。
//        int writer = left;
//        int reader = right - 1;
//        while (writer <= reader) {
//            if (this.nums[reader] < pivot) {
//                swap(writer, reader);
//                writer++;
//            } else {
//                reader--;
//            }
//        }
        
        // move pivot back to its final position (i.e. the writer)
        swap(writer, right); // writer points to the next place to right, finally it points to right part.
        return writer;
    }
    
    private void swap(int l, int r) {
        int tmp = this.nums[l];
        this.nums[l] = this.nums[r];
        this.nums[r] = tmp;
    }
    
    // [1,5,3,4,2]
    // [1,5,3,2,4] pivot = 4, l=1, r=2    r < pivot
    // [2,5,3,1,4] l=5,r=1, r < pivot
    // [2,1,3,5,4] l=3,r=5, r >= pivot
    // [2,1,3,5,4] l=3,r=3, r < pivot
    // [2,1,3,5,4] l=5,r=3  exit.  l points to the first element of right side.
    // [2,1,3,4,5] swap(l,right)
}
